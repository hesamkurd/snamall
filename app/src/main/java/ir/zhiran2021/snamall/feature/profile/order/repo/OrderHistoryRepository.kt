package ir.zhiran2021.snamall.feature.profile.order.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseOrderHistory

interface OrderHistoryRepository {
    fun getOrderHistory():Single<List<ResponseOrderHistory>>
}