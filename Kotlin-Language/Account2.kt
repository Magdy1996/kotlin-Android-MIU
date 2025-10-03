package lab2

class Account2 (
    private var balance : Double= 0.0,
    private var name : String = "",
    private var accountNumber : String = ""
){



    override fun toString(): String {
        return "Account(balance=$balance, name='$name', accountNumber='$accountNumber')"
    }



    fun main(){
        val account = Account2(100.0, "Magdy", "123456789")
//        account.balance = 100.0
//        account.name = "Magdy"
//        account.accountNumber = "123456789"
        println(account)

    }
}