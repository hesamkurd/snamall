package ir.zhiran2021.snamall.feature.profile.orderdelivery.orderdeliverydetail.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseOrderDetailDelivery

interface OrderDetailDeliveryDataSource {
    fun getOrderDetailDelivery(refId:String):Single<ResponseOrderDetailDelivery>
}