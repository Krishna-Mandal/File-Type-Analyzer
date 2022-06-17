fun printNameOfTerminatedThread(threads: List<Thread>) {
    threads.forEach {
        it.join()
        if (it.state == Thread.State.TERMINATED) {
            println(it.name)
        }
    }
}
