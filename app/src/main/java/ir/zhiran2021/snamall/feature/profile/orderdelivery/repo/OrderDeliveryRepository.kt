package ir.zhiran2021.snamall.feature.profile.orderdelivery.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseOrderDelivery

interface OrderDeliveryRepository {
    fun getOrderDelivery():Single<List<ResponseOrderDelivery>>
}