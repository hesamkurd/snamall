package ir.zhiran2021.snamall.feature.home.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseBanners

interface BannersDataSource {

    fun getBanners(): Single<List<ResponseBanners>>
}