package ir.mamhesam.snamall.feature.profile.infouser.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseInfoUser

class RemoteInfoUserDataSource(val apiService: ApiService):InfoUserDataSource {
    override fun getInfoUser(): Single<ResponseInfoUser> = apiService.getInfoUser()
}