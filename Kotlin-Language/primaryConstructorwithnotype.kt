package lab2

class Account3 (
      balance : Double= 0.0,
      name : String = "",
      accountNumber : String = ""
){


    init{
        println("In init $balance $name $accountNumber")
    }

    var balance : Double = balance
    var name : String = name
    var accountNumber : String = accountNumber

    override fun toString(): String {
        return "Account(balance=$balance, name='$name', accountNumber='$accountNumber')"
    }



    fun main(){
        val account = Account3()
        account.balance = 100.0
        account.name = "Magdy"
       account.accountNumber = "123456789"
//        println(account)

    }
}