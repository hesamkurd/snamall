package ir.mamhesam.snamall.feature.home.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseBanners

class RemoteBannersDataSource(val apiService: ApiService): BannersDataSource {
    override fun getBanners(): Single<List<ResponseBanners>> = apiService.getBanners()
}