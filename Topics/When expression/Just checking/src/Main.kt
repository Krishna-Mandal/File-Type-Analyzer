const val JAVA = 1
const val KOTLIN = 2
const val SCALA = 3
const val PYTHON = 4

fun main() {
    println(
        when (readln().toInt()) {
            KOTLIN -> "Yes!"
            JAVA, SCALA, PYTHON -> "No!"
            else -> "Unknown number"
        }
    )
}