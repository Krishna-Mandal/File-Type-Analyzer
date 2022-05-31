class CountDownCounter(var count: Int) {
    fun decrement() {
        synchronized(this) {
            count--
        }
    }
}