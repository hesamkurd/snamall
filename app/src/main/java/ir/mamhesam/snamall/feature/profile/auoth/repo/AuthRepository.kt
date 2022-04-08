package ir.mamhesam.snamall.feature.profile.auoth.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseAddFavorite
import ir.mamhesam.snamall.data.ResponseCheckUser
import ir.mamhesam.snamall.data.ResponseLogin
import ir.mamhesam.snamall.data.ResponseRegister

interface AuthRepository {
    fun checkUser(phone: String): Single<ResponseCheckUser>
    fun register(phone: String, name: String): Single<ResponseRegister>
    fun login(phone: String):Single<ResponseLogin>

    fun loadToken()
    fun checkLogin(): Boolean

    fun addToFavorite(productId:Int): Single<ResponseAddFavorite>
}