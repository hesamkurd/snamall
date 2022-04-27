package ir.mamhesam.snamall.feature.profile.order.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseOrderHistory
import ir.mamhesam.snamall.feature.profile.order.source.OrderHistoryDataSource

class OrderHistoryRepositoryImpl(val remoteOrderHistoryDataSource: OrderHistoryDataSource):OrderHistoryRepository {
    override fun getOrderHistory(): Single<List<ResponseOrderHistory>> = remoteOrderHistoryDataSource.getOrderHistory()
}