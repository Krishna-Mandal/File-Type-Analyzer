class Account {

    var balance = 0L

    @Synchronized
    fun withdraw(amount: Long): Boolean {
        return if (amount < balance) {
            balance -= amount
            true
        } else {
            false
        }
    }

    @Synchronized
    fun deposit(amount: Long) {
        balance += amount
    }
}