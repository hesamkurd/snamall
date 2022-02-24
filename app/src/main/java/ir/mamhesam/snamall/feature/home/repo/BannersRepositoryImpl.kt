package ir.mamhesam.snamall.feature.home.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseBanners
import ir.mamhesam.snamall.feature.home.source.BannersDataSource

class BannersRepositoryImpl(val remoteBannersDataSource: BannersDataSource): BannersRepository {
    override fun getBanners(): Single<List<ResponseBanners>> = remoteBannersDataSource.getBanners()
}