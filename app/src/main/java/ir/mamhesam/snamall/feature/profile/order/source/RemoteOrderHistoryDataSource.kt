package ir.mamhesam.snamall.feature.profile.order.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseOrderHistory

class RemoteOrderHistoryDataSource(val apiService: ApiService):OrderHistoryDataSource {
    override fun getOrderHistory(): Single<List<ResponseOrderHistory>> = apiService.getOrderHistory()
}