fun main() {
    val (first, op, second) = readln().split(" ")

    println(
        when (op) {
            "+" -> first.toLong() + second.toLong()
            "-" -> first.toLong() - second.toLong()
            "*" -> first.toLong() * second.toLong()
            "/" ->
                if (second.toInt() == 0) {
                    "Division by 0!"
                } else {
                    first.toLong() / second.toLong()
                }

            else -> "Unknown operator"
        }
    )
}