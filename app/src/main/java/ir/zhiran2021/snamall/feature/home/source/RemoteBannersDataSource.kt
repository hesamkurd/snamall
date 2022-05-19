package ir.zhiran2021.snamall.feature.home.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseBanners

class RemoteBannersDataSource(val apiService: ApiService): BannersDataSource {
    override fun getBanners(): Single<List<ResponseBanners>> = apiService.getBanners()
}