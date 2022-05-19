package ir.zhiran2021.snamall.feature.profile.order.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseOrderHistory

class RemoteOrderHistoryDataSource(val apiService: ApiService):OrderHistoryDataSource {
    override fun getOrderHistory(): Single<List<ResponseOrderHistory>> = apiService.getOrderHistory()
}