package ir.mamhesam.snamall.feature.home.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseBanners

interface BannersRepository {

    fun getBanners(): Single<List<ResponseBanners>>
}