package ir.zhiran2021.snamall.feature.profile.auoth.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.*

interface AuthDataSource {
    fun checkUser(phone: String): Single<ResponseCheckUser>
    fun register(phone: String, name: String): Single<ResponseRegister>
    fun login(phone: String):Single<ResponseLogin>

    //

    fun saveToken(token: String)
    fun loadToken()
    fun checkLogin(): Boolean

    fun addToFavorite(productId:Int): Single<ResponseAddFavorite>
    fun addToCart(product_id: Int,color_id: Int,size_id: Int): Single<ResponseInsertComment>



}