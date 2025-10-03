package lab2

class Account {

    var balance : Double= 0.0
    var name : String = ""
    var accountNumber : String = ""


    override fun toString(): String {
        return "Account(balance=$balance, name='$name', accountNumber='$accountNumber')"
    }



    fun main(){
        val account = Account()
        account.balance = 100.0
        account.name = "Magdy"
        account.accountNumber = "123456789"
        println(account)

    }
}