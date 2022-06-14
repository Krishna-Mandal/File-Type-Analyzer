package analyzer

import java.io.File
import kotlin.concurrent.thread
import kotlin.math.pow

const val ZERO = 0
const val ONE = 1
const val TWO = 2
const val UNKNOWN = "Unknown file type"

data class Option(
    val fileName: String,
    val filePattern: String,
    val resultString: String,
    val prio: Int
)

fun main(args: Array<String>) {

    val folderName = File(args[0])
    val threads = mutableListOf<Thread>()
    folderName.walkTopDown().filter { it.isFile }.forEach {
        args[0] = it.absolutePath
        val options = parseArgument(args)
        options.forEach{
            val searchThread = thread(start = true, block = { kmpSearch(it) })
            threads.add(searchThread)
        }

    }

    for (thread in threads) {
        thread.join()
    }
}

fun parseArgument(args: Array<String>): MutableList<Option> {
    if (args.size < TWO) {
        throw Exception("Wrong number of Input")
    }
    val options = mutableListOf<Option>()
    val patternFile = File(args[ONE])
    patternFile.forEachLine {
        val (prio, filePattern, resultString) = it.split(";")
        options.add(
            Option(
                fileName = args[ZERO],
                filePattern = filePattern.replace("\"", ""),
                resultString = resultString.replace("\"", ""),
                prio = prio.toInt()
            )
        )
    }

    return options
}

fun getCode(char: Char): Int {
    return char.code
}

fun calculateHash(subString: String, a: Int, m: Int): Int {
    var hash = 0
    for (index in subString.indices) {
        val calculatedHash = (getCode(subString[index]) * a.toDouble().pow(index)).toInt()
        hash += calculatedHash
    }

    return hash % m
}

fun calculateRollingHash(subString: String, a: Int, m: Int, lastChar: Char?, lastHash: Int): Int {
    return if (lastChar == null) {
        calculateHash(subString, a, m)
    } else {
        val removeHash = (getCode(lastChar) * a.toDouble().pow(subString.lastIndex)).toInt()
        val nethash = ((lastHash - removeHash) * a + getCode(subString.first()))
        var rem = nethash % m
        if (rem < 0) {
            rem += m
        } else {
            rem
        }

        return rem
    }
}

fun rabinKarpSearch(option: Option) {
    val file = File(option.fileName)
    val text = file.readText()
    val pattern = option.filePattern

    var startIndex = text.length - pattern.length
    var endIndex = text.length
    var lastChar: Char? = null
    var lastHash = 0
    val patterHash = calculateHash(pattern, 3, 53)

    while(startIndex >= 0) {
        val subString = text.substring(startIndex, endIndex)
        val subStrHash = calculateRollingHash(subString = subString, 3, 53, lastChar, lastHash)
        if (patterHash == subStrHash && pattern == subString) {
            println("${file.name}: ${option.resultString}")
            return
        }
        lastChar = subString.last()
        lastHash = subStrHash
        --startIndex
        --endIndex
    }

    println("${file.name}: $UNKNOWN")
    return
}

fun kmpSearch(option: Option) {
    val file = File(option.fileName)
    val text = file.readText()

    val textLength = text.length
    val pattern = option.filePattern.toCharArray()
    val patternLength = pattern.size
    val prefixFunction = MutableList(patternLength) {0}
    computePrefixFunction(pattern, prefixFunction)

    var textIndex = 0
    var patternIndex = 0

    while (textIndex < textLength) {
        if (pattern[patternIndex] == text[textIndex]) {
            ++textIndex
            ++patternIndex
        }

        if (patternIndex == patternLength) {
            println("${file.name}: ${option.resultString}")
            return
        } else if (textIndex < textLength && pattern[patternIndex] != text[textIndex]) {
            if (patternIndex != 0) {
                patternIndex = prefixFunction[patternIndex - 1]
            } else {
                ++textIndex
            }
        }

    }

    println("${file.name}: $UNKNOWN")
    return
}

fun computePrefixFunction(filePattern: CharArray, prefixFunction: MutableList<Int>) {

    // Length of previous prefix suffix
    var length = 0
    val patternLength = filePattern.size
    var index = 1
    // First prefix function is always 0
    prefixFunction[ZERO] = ZERO

    while (index < patternLength) {
        if (filePattern[index] == filePattern[length]) {
            ++length
            prefixFunction[index] = length
            ++index
        } else {
            if (length != 0) {
                length = prefixFunction[length - 1]
            } else {
                prefixFunction[index] = ZERO
                ++index
            }
        }
    }
}