package ir.zhiran2021.snamall.feature.profile.orderdelivery.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseOrderDelivery

interface OrderDeliveryDataSource {
    fun getOrderDelivery():Single<List<ResponseOrderDelivery>>
}