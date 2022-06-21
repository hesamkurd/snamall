package ir.zhiran2021.snamall.feature.profile.auoth.privacy.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponsePrivacy

class RemotePrivacyDataSource(val apiService: ApiService):PrivacyDataSource {
    override fun getPrivacy(): Single<List<ResponsePrivacy>> = apiService.getPrivacy()
}