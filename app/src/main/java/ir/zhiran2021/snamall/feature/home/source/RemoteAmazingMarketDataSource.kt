package ir.zhiran2021.snamall.feature.home.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseAmazingMarket

class RemoteAmazingMarketDataSource(val apiService: ApiService):AmazingMarketDataSource {
    override fun getAmazingMarket(): Single<List<ResponseAmazingMarket>> = apiService.getAmazingMarket()
}