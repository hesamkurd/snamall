package ir.zhiran2021.snamall.feature.search.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseSearch
import ir.zhiran2021.snamall.feature.search.source.SearchDataSource

class SearchRepositoryImpl(val remoteSearchDataSource: SearchDataSource):SearchRepository {
    override fun getSearch(search: String): Single<ResponseSearch> = remoteSearchDataSource.getSearch(search)
}