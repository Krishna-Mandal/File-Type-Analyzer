package analyzer

import java.io.File
import kotlin.concurrent.thread

const val ZERO = 0
const val ONE = 1
const val TWO = 2
const val THREE = 3
const val UNKNOWN = "Unknown file type"

data class Option(
    val fileName: String,
    val filePattern: String,
    val resultString: String
)

fun main(args: Array<String>) {

    val folderName = File(args[0])
    val threads = mutableListOf<Thread>()
    folderName.walkTopDown().filter { it.isFile }.forEach {
        args[0] = it.absolutePath
        val option = parseArgument(args)
        val searchThread = thread(start = true, block = {kmpSearch(option)})
        threads.add(searchThread)
    }

    for (thread in threads) {
        thread.join()
    }
}

fun parseArgument(args: Array<String>): Option {
    if (args.size < THREE) {
        throw Exception("Wrong number of Input")
    }

    return Option(
        args[ZERO],
        args[ONE],
        args[TWO]
    )
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