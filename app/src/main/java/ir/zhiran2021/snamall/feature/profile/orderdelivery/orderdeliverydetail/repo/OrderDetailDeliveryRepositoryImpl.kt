package ir.zhiran2021.snamall.feature.profile.orderdelivery.orderdeliverydetail.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseOrderDetailDelivery
import ir.zhiran2021.snamall.feature.profile.orderdelivery.orderdeliverydetail.source.OrderDetailDeliveryDataSource

class OrderDetailDeliveryRepositoryImpl(val remoteOrderDetailDeliveryDataSource: OrderDetailDeliveryDataSource):OrderDetailDeliveryRepository {
    override fun getOrderDetailDelivery(refId: String): Single<ResponseOrderDetailDelivery> = remoteOrderDetailDeliveryDataSource.getOrderDetailDelivery(refId)
}