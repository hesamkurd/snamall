package ir.mamhesam.snamall.feature.home.allamazing.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseAllAmazing
import ir.mamhesam.snamall.feature.home.allamazing.source.AllAmazingDataSource

class AllAmazingRepositoryImpl(val remoteAllAmazingDataSource: AllAmazingDataSource):AllAmazingRepository {
    override fun allAmazing(sort: Int): Single<List<ResponseAllAmazing>> = remoteAllAmazingDataSource.allAmazing(sort)
}