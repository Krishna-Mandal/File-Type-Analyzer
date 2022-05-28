class MessageNotifier(var msg: String, var number: Int) : Thread() {
    override fun run() {
        repeat(number) {
            println(msg)
        }
    }
}