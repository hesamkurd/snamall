package ir.zhiran2021.snamall.feature.home.allamazingmarket.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseAllAmazing
import ir.zhiran2021.snamall.data.ResponseAllAmazingMarket
import ir.zhiran2021.snamall.feature.home.allamazing.repo.AllAmazingRepository
import ir.zhiran2021.snamall.feature.home.allamazingmarket.source.AllAmazingMarketDataSource

class AllAmazingMarketRepositoryImpl(val remoteAllAmazingMarketDataSource: AllAmazingMarketDataSource):AllAmazingMarketRepository {
    override fun getAllAmazingMarket(sort: Int): Single<List<ResponseAllAmazingMarket>> = remoteAllAmazingMarketDataSource.getAllAmazingMarket(sort)
}