package ir.zhiran2021.snamall.feature.home.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseAmazingMarket
import ir.zhiran2021.snamall.feature.home.source.AmazingMarketDataSource

class AmazingMarketRepositoryImpl(val remoteAmazingMarketDataSource: AmazingMarketDataSource):AmazingMarketRepository {
    override fun getAmazingMarket(): Single<List<ResponseAmazingMarket>> = remoteAmazingMarketDataSource.getAmazingMarket()
}