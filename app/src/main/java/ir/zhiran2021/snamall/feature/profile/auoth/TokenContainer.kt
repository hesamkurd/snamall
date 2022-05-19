package ir.zhiran2021.snamall.feature.profile.auoth

object TokenContainer {
    var token: String?=null
    private set
    fun updateToken(token:String?){
        this.token = token
    }
}