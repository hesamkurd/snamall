package ir.mamhesam.snamall.feature.home.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseBannerType2
import ir.mamhesam.snamall.feature.home.source.BannerType2DataSource

class BannerType2RepositoryImpl(val remoteBannerType2DataSource: BannerType2DataSource): BannerType2Repository {
    override fun getBannerType2(): Single<List<ResponseBannerType2>> = remoteBannerType2DataSource.getBannerType2()
}