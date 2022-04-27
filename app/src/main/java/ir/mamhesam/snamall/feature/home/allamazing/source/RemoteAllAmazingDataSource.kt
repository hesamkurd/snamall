package ir.mamhesam.snamall.feature.home.allamazing.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseAllAmazing

class RemoteAllAmazingDataSource(val apiService: ApiService):AllAmazingDataSource {
    override fun allAmazing(sort: Int): Single<List<ResponseAllAmazing>> = apiService.allAmazing(sort)
}