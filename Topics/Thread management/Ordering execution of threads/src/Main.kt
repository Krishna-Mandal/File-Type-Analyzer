fun invokeMethods(t1: Thread, t2: Thread, t3: Thread) {
    try {
        t3.start()
        t3.join()

        t2.start()
        t2.join()

        t1.start()
        t1.join()

    } catch (e: InterruptedException) {
        println("Exception in thread \"main\" java.lang.RuntimeException: All threads must be terminated before ending the implemented method")
    }
}
