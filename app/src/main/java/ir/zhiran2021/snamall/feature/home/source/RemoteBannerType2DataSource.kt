package ir.zhiran2021.snamall.feature.home.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseBannerType2

class RemoteBannerType2DataSource(val apiService: ApiService): BannerType2DataSource {
    override fun getBannerType2(): Single<List<ResponseBannerType2>> = apiService.getBannerType2()
}