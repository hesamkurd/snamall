package ir.mamhesam.snamall.feature.profile.auoth.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.*

interface AuthRepository {
    fun checkUser(phone: String): Single<ResponseCheckUser>
    fun register(phone: String, name: String): Single<ResponseRegister>
    fun login(phone: String):Single<ResponseLogin>

    fun loadToken()
    fun checkLogin(): Boolean

    fun addToFavorite(productId:Int): Single<ResponseAddFavorite>
    fun addToCart(product_id: Int,color_id: Int,size_id: Int): Single<ResponseInsertComment>
}