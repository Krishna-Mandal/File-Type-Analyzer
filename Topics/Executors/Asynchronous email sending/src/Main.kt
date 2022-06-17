import java.util.concurrent.Executors

fun main() {
    val messageCounts: Int = readln().toInt()
    val messages: MutableList<String> = mutableListOf<String>() 
    for (i in 0 until messageCounts) {
        messages.add(readln())
    }
    val sender: MailSender = MockMailSender()
    asyncSend(sender, messages)
}

fun asyncSend(sender: MailSender, messages: List<String>) {
    val executor = Executors.newSingleThreadExecutor()
    messages.forEach {
        executor.submit { sender.send(it) }
    }
    executor.shutdown()
}

// Do not change it
interface MailSender {
    fun send(message: String)
}

// The class simulates mail sending
// Do not change it
class MockMailSender : MailSender {
    override fun send(message: String) {
        println("Message $message was sent")
    }
}