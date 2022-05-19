package ir.zhiran2021.snamall.feature.home.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseBanners
import ir.zhiran2021.snamall.feature.home.source.BannersDataSource

class BannersRepositoryImpl(val remoteBannersDataSource: BannersDataSource): BannersRepository {
    override fun getBanners(): Single<List<ResponseBanners>> = remoteBannersDataSource.getBanners()
}