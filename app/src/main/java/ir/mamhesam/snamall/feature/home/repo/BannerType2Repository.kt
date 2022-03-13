package ir.mamhesam.snamall.feature.home.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseBannerType2

interface BannerType2Repository {
    fun getBannerType2(): Single<List<ResponseBannerType2>>
}