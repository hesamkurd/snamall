package ir.zhiran2021.snamall.feature.home.allamazingmarket.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseAllAmazingMarket

class RemoteAllAmazingMarketDataSource(val apiService: ApiService):AllAmazingMarketDataSource {
    override fun getAllAmazingMarket(sort: Int): Single<List<ResponseAllAmazingMarket>> = apiService.getAllAmazingMarket(sort)
}