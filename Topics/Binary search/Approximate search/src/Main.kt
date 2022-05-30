import java.io.File
import kotlin.math.abs

// write your code here

fun main(args: Array<String>) {
    val file = File("/Users/mandal/IdeaProjects/File Type Analyzer/Topics/Binary search/Approximate search/data/dataset/input.txt")
    var text = file.readLines()
    val inputList = text.first().split(" ").map { it.toString().toInt() }.toList()
    val searchList = text.last().split(" ").map { it.toString().toInt() }.toList()
    var lastDiff = -1
    var lastInput = Int.MIN_VALUE

    for (search in searchList) {
        for (input in inputList) {
            val currDiff = abs(input - search)
            if (lastDiff == -1) {
                lastDiff = currDiff
                lastInput = input
            } else if (currDiff < lastDiff) {
                lastDiff = currDiff
                lastInput = input
            } else if (input == lastInput) {
                lastDiff = currDiff
                lastInput = input
            } else {
                print("$lastInput ")
                lastDiff = -1
                lastInput = Int.MIN_VALUE
                break
            }
        }
    }

}
