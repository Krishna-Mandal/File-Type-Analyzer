fun startRunnables(runnables: Array<Runnable>) {
    runnables.forEach {
        Thread(it).start()
    }
}