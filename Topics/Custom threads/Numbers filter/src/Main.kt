import kotlin.concurrent.thread

fun main() {
    val numbersFilter = thread(name = "numbersFilter",
        block = {
            checkEven()
        }
    )
}

fun checkEven() {
    do {
        val input = readln().toInt()
        if (input != 0 && input % 2 == 0) {
            println(input)
        }
    } while (input != 0)
}
