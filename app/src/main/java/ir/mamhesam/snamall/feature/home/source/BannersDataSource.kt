package ir.mamhesam.snamall.feature.home.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseBanners

interface BannersDataSource {

    fun getBanners(): Single<List<ResponseBanners>>
}