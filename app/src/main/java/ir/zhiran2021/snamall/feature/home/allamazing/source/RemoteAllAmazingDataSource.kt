package ir.zhiran2021.snamall.feature.home.allamazing.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseAllAmazing

class RemoteAllAmazingDataSource(val apiService: ApiService):AllAmazingDataSource {
    override fun allAmazing(sort: Int): Single<List<ResponseAllAmazing>> = apiService.allAmazing(sort)
}