package ir.zhiran2021.snamall.feature.profile.infouser.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseInfoUser

class RemoteInfoUserDataSource(val apiService: ApiService):InfoUserDataSource {
    override fun getInfoUser(): Single<ResponseInfoUser> = apiService.getInfoUser()
}