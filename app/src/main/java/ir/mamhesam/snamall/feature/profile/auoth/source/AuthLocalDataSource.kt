package ir.mamhesam.snamall.feature.profile.auoth.source

import android.content.SharedPreferences
import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseAddFavorite
import ir.mamhesam.snamall.data.ResponseCheckUser
import ir.mamhesam.snamall.data.ResponseLogin
import ir.mamhesam.snamall.data.ResponseRegister
import ir.mamhesam.snamall.feature.profile.auoth.TokenContainer

class AuthLocalDataSource(val sharedPreferences: SharedPreferences): AuthDataSource {
    override fun checkUser(phone: String): Single<ResponseCheckUser> {
        TODO("Not yet implemented")
    }

    override fun register(phone: String, name: String): Single<ResponseRegister> {
        TODO("Not yet implemented")
    }

    override fun login(phone: String): Single<ResponseLogin> {
        TODO("Not yet implemented")
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit().apply {
            putString("token", token)
        }.apply()
    }

    override fun loadToken() {
        TokenContainer.updateToken(sharedPreferences.getString("token", null))
    }

    override fun checkLogin(): Boolean {
        if (sharedPreferences.getString("token","")!=""){
            return true
        }else{
            return false
        }
    }

    override fun addToFavorite(productId: Int): Single<ResponseAddFavorite> {
        TODO("Not yet implemented")
    }
}