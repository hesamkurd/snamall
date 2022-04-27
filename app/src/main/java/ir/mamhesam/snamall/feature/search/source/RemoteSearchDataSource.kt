package ir.mamhesam.snamall.feature.search.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseSearch

class RemoteSearchDataSource(val apiService: ApiService): SearchDataSource {
    override fun getSearch(search: String): Single<ResponseSearch> = apiService.search(search)
}