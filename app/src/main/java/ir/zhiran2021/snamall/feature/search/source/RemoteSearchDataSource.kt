package ir.zhiran2021.snamall.feature.search.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseSearch

class RemoteSearchDataSource(val apiService: ApiService): SearchDataSource {
    override fun getSearch(search: String): Single<ResponseSearch> = apiService.search(search)
}