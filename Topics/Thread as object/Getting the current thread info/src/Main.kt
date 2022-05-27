fun printCurrentThreadInfo() {
    val currentThread = Thread.currentThread()
    println("name: ${currentThread.name}")
    println("priority: ${currentThread.priority}")
}