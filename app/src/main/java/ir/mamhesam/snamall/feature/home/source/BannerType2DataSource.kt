package ir.mamhesam.snamall.feature.home.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseBannerType2

interface BannerType2DataSource {
    fun getBannerType2(): Single<List<ResponseBannerType2>>
}