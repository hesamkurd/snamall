package ir.zhiran2021.snamall.feature.profile.order.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseOrderHistory
import ir.zhiran2021.snamall.feature.profile.order.source.OrderHistoryDataSource

class OrderHistoryRepositoryImpl(val remoteOrderHistoryDataSource: OrderHistoryDataSource):OrderHistoryRepository {
    override fun getOrderHistory(): Single<List<ResponseOrderHistory>> = remoteOrderHistoryDataSource.getOrderHistory()
}