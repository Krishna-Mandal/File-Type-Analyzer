fun main() {
    val t1 = Thread(RunnableWorker(), "worker-thread1")
    val t2 = Thread(RunnableWorker(), "worker-thread2")
    val t3 = Thread(RunnableWorker(), "worker-thread3")
    t1.start()
    t2.start()
    t3.start()
}

// Don't change the code below       
class RunnableWorker : Runnable {
    override fun run() {
        val name = Thread.currentThread().name
        if (name.startsWith("worker-")) {
            println("too hard calculations...")
        } else {
            return
        }
    }
}