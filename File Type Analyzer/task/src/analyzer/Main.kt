package analyzer

import java.io.File

const val ZERO = 0
const val ONE = 1
const val TWO = 2
const val THREE = 3
const val FOUR = 4
const val UNKNOWN = "Unknown file type"

enum class ALGORITHM {
    NAIVE,
    KMP
}
data class Option(
    val algo: ALGORITHM,
    val fileName: String,
    val filePattern: String,
    val resultString: String
)
fun main(args: Array<String>) {

    val option = parseArgument(args = args)
    val start = System.nanoTime()
    val result = findFileType(option)
    val elapsed = System.nanoTime() - start
    println(result)
    println("It took $elapsed seconds")
}

fun parseArgument(args: Array<String>): Option {
    if (args.size < FOUR) {
        throw Exception("Wrong number of Input")
    }

    return Option(
        ALGORITHM.valueOf(args[ZERO].substring(TWO).uppercase()),
        args[ONE],
        args[TWO],
        args[THREE]
    )
}

fun naiveSearch(option: Option): String {
    val file = File(option.fileName)
    var found = false
    file.readLines().forEach() { if (it.contains(option.filePattern)) found = true }

    return if (found) option.resultString else UNKNOWN
}

fun kmpSearch(option: Option): String {
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
            return option.resultString
        } else if (textIndex < textLength && pattern[patternIndex] != text[textIndex]) {
            if (patternIndex != 0) {
                patternIndex = prefixFunction[patternIndex - 1]
            } else {
                ++textIndex
            }
        }

    }

    return UNKNOWN
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

fun findFileType(option: Option): String {
    return when (option.algo) {
        ALGORITHM.NAIVE -> naiveSearch(option)
        ALGORITHM.KMP -> kmpSearch(option)
    }
}