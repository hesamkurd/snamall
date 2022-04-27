package ir.mamhesam.snamall.feature.search.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseSearch
import ir.mamhesam.snamall.feature.search.source.SearchDataSource

class SearchRepositoryImpl(val remoteSearchDataSource: SearchDataSource):SearchRepository {
    override fun getSearch(search: String): Single<ResponseSearch> = remoteSearchDataSource.getSearch(search)
}