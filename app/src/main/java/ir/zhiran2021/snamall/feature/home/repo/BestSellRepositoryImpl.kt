package ir.zhiran2021.snamall.feature.home.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseBestSellProduct
import ir.zhiran2021.snamall.feature.home.source.BestSellDataSource

class BestSellRepositoryImpl(val remoteBestSellDataSource: BestSellDataSource):BestSellRepository {
    override fun getBestSell(): Single<List<ResponseBestSellProduct>> = remoteBestSellDataSource.getBestSell()
}