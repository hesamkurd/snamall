package ir.mamhesam.snamall.feature.profile.order.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseOrderHistory

interface OrderHistoryRepository {
    fun getOrderHistory():Single<List<ResponseOrderHistory>>
}