package ir.zhiran2021.snamall.feature.profile.auoth.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.*
import ir.zhiran2021.snamall.feature.profile.auoth.TokenContainer
import ir.zhiran2021.snamall.feature.profile.auoth.source.AuthDataSource
import ir.zhiran2021.snamall.feature.profile.auoth.source.AuthLocalDataSource

class AuthRepositoryImpl(val remoteAuthDataSource: AuthDataSource, val authLocalDataSource: AuthLocalDataSource): AuthRepository {
    override fun checkUser(phone: String): Single<ResponseCheckUser> = remoteAuthDataSource.checkUser(phone)

    override fun register(phone: String, name: String): Single<ResponseRegister> {
        return remoteAuthDataSource.register(phone, name).doOnSuccess {
            TokenContainer.updateToken(it.token)
            authLocalDataSource.saveToken(it.token)
        }
    }

    override fun login(phone: String): Single<ResponseLogin>{
        return remoteAuthDataSource.login(phone).doOnSuccess {
            TokenContainer.updateToken(it.token)
            authLocalDataSource.saveToken(it.token)
        }
    }

    override fun loadToken() {
        authLocalDataSource.loadToken()
    }

    override fun checkLogin(): Boolean {
        return authLocalDataSource.checkLogin()
    }

    override fun addToFavorite(productId: Int): Single<ResponseAddFavorite> = remoteAuthDataSource.addToFavorite(productId)
    override fun addToCart(
        product_id: Int,
        color_id: Int,
        size_id: Int
    ): Single<ResponseInsertComment> = remoteAuthDataSource.addToCart(product_id, color_id, size_id)
}