package ir.zhiran2021.snamall.feature.home.allamazing.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseAllAmazing
import ir.zhiran2021.snamall.feature.home.allamazing.source.AllAmazingDataSource

class AllAmazingRepositoryImpl(val remoteAllAmazingDataSource: AllAmazingDataSource):AllAmazingRepository {
    override fun allAmazing(sort: Int): Single<List<ResponseAllAmazing>> = remoteAllAmazingDataSource.allAmazing(sort)
}