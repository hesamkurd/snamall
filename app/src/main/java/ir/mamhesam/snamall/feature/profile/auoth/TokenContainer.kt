package ir.mamhesam.snamall.feature.profile.auoth

object TokenContainer {
    var token: String?=null
    private set
    fun updateToken(token:String?){
        this.token = token
    }
}