import java.util.concurrent.*

fun main() {
    val executor: ExecutorService = Executors.newSingleThreadExecutor()
    val numbers: List<Int> = readln().split(" ").map { el -> el.toInt() }
    for (number in numbers) {
        executor.submit(PrintIfPrimeTask(number))
    }

    executor.shutdown()
}

class PrintIfPrimeTask(private val number: Int) : Runnable {
    override fun run() {
        if (number == 2 || number == 3) {
            println(number)
            return
        }

        if (number <= 1 || number % 2 == 0 || number % 3 == 0) return

        var index = 5
        while (index * index <= number) {
            if (number % index == 0 || number % (index + 2) == 0)
                return

            index += 6
        }

        println(number)
    }
}