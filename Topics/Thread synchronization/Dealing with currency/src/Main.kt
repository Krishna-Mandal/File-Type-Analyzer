class BankAccount {
    var balance = 0L

    fun addMoney(action: String) {
        // synchronize adding money with synchronized()
        when (action) {
            "1" ->  synchronized(this) {
                addDollar()
            }
            "2" -> synchronized(this) {
                addEuro()
            }
            else -> synchronized(this) {
                addRuble()
            }
        }
    }
    fun addDollar() {
        balance += 75
    }
    fun addEuro() {
        balance += 84
    }
    fun addRuble() {
        balance += 1
    }
}