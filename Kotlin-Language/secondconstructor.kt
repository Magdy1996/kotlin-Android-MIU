package lab2

class Account4 (
    balance : Double,
    name : String ,
    accountNumber : String
){

    constructor(accountNumber: String) : this(0.0, "unknown", accountNumber= accountNumber)

//    constructor(accountNumber:String , name:String) : this(balance = 0.0 , name = name , accountNumber= accountNumber)


    //
    constructor(accountNumber : String, name : String) : this( accountNumber = accountNumber)


//    init{
//        println("In init $balance $name $accountNumber")
//    }

    var balance : Double = balance
    var name : String = name
    var accountNumber : String = accountNumber

    override fun toString(): String {
        return "Account(balance=$balance, name='$name', accountNumber='$accountNumber')"
    }



    fun main(){
//        val account = Account4()
//        account.balance = 100.0
//        account.name = "Magdy"
//        account.accountNumber = "123456789"
////        println(account)

    }
}