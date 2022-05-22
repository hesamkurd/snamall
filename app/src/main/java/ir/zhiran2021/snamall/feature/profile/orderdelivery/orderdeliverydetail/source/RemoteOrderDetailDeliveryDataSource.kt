package ir.zhiran2021.snamall.feature.profile.orderdelivery.orderdeliverydetail.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseOrderDetailDelivery

class RemoteOrderDetailDeliveryDataSource(val apiService: ApiService):OrderDetailDeliveryDataSource {
    override fun getOrderDetailDelivery(refId: String): Single<ResponseOrderDetailDelivery> = apiService.getOrderDetailDelivery(refId)
}