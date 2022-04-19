package ir.mamhesam.snamall.feature.profile.auoth.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.*

class RemoteAuthDataSource(val apiService: ApiService): AuthDataSource {
    override fun checkUser(phone: String): Single<ResponseCheckUser> = apiService.checkUser(phone)
    override fun register(phone: String, name: String): Single<ResponseRegister> = apiService.register(phone, name)
    override fun login(phone: String): Single<ResponseLogin> = apiService.login(phone)


    override fun saveToken(token: String) {
        TODO("Not yet implemented")
    }

    override fun loadToken() {
        TODO("Not yet implemented")
    }

    override fun checkLogin(): Boolean {
        TODO("Not yet implemented")
    }

    override fun addToFavorite(productId: Int): Single<ResponseAddFavorite> = apiService.addToFavorite(productId)
    override fun addToCart(
        product_id: Int,
        color_id: Int,
        size_id: Int
    ): Single<ResponseInsertComment> = apiService.addToCart(product_id, color_id, size_id)
}