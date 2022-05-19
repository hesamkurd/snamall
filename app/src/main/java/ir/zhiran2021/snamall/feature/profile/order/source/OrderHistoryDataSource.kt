package ir.zhiran2021.snamall.feature.profile.order.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseOrderHistory

interface OrderHistoryDataSource {
    fun getOrderHistory():Single<List<ResponseOrderHistory>>
}