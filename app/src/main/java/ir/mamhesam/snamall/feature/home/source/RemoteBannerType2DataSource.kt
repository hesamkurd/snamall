package ir.mamhesam.snamall.feature.home.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseBannerType2

class RemoteBannerType2DataSource(val apiService: ApiService): BannerType2DataSource {
    override fun getBannerType2(): Single<List<ResponseBannerType2>> = apiService.getBannerType2()
}