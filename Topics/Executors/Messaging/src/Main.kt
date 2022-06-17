import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/* Do not change this class */
data class Message(val from: String, val to: String, val text: String)

/* Do not change this interface */
interface AsyncMessageSender {
    fun sendMessages(messages: List<Message>)
    fun stop()
}

class AsyncMessageSenderImpl(val repeatFactor: Int) : AsyncMessageSender {
    val executor: ExecutorService = Executors.newSingleThreadExecutor()

    override fun sendMessages(messages: List<Message>) {
        for (msg in messages) {
            println("(${msg.from}>${msg.to}): ${msg.text}")
            println("(${msg.from}>${msg.to}): ${msg.text}")
            executor.submit {
                println("(${msg.from}>${msg.to}): ${msg.text}") // do not change it
            }
        }
    }

    override fun stop() {
        executor.shutdown()
        executor.awaitTermination(500, TimeUnit.MILLISECONDS)
    }
}