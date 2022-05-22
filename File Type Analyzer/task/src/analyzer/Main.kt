package analyzer

import java.io.File

const val ZERO = 0
const val ONE = 1
const val TWO = 2
const val UNKNOWN = "Unknown file type"

fun main(args: Array<String>) {
    if (args.size < 3) {
        throw Exception("Wrong number of Input")
    }

    val fileName = args[ZERO]
    val filePattern = args[ONE]
    val resultString = args[TWO]

    println(findFileType(fileName, filePattern, resultString))
}

fun findFileType(fileName: String, filePattern: String, resultString: String): String {
    val file = File(fileName)
    var found = false
    file.readLines().forEach() {
        if (it.contains(filePattern)) {
            found = true
        }
    }

    return if (found) resultString else UNKNOWN
}

