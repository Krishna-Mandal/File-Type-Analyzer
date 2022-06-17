fun findAndStartThread(threads: List<Thread>) {
    threads.forEach{
        if (it.state == Thread.State.NEW) {
            it.start()
        }

        it.join()
    }
}