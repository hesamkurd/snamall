package ir.zhiran2021.snamall.feature.home.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseBanners

interface BannersRepository {

    fun getBanners(): Single<List<ResponseBanners>>
}