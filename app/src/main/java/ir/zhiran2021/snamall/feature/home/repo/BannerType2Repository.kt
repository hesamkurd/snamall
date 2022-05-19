package ir.zhiran2021.snamall.feature.home.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseBannerType2

interface BannerType2Repository {
    fun getBannerType2(): Single<List<ResponseBannerType2>>
}