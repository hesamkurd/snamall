package ir.mamhesam.snamall.feature.profile.order.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseOrderHistory

interface OrderHistoryDataSource {
    fun getOrderHistory():Single<List<ResponseOrderHistory>>
}