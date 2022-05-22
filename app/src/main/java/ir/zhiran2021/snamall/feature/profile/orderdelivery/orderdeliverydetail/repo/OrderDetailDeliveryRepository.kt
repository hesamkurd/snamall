package ir.zhiran2021.snamall.feature.profile.orderdelivery.orderdeliverydetail.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseOrderDetailDelivery

interface OrderDetailDeliveryRepository {
    fun getOrderDetailDelivery(refId:String):Single<ResponseOrderDetailDelivery>
}