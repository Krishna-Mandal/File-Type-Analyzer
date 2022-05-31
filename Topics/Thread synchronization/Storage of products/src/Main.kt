class Storage {
    var productCount = 0

    fun addProduct() {
        synchronized(this) {
            productCount++
        }
    }
}
