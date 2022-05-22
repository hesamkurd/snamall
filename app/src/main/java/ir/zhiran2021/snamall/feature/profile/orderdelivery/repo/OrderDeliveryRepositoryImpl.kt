package ir.zhiran2021.snamall.feature.profile.orderdelivery.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseOrderDelivery
import ir.zhiran2021.snamall.data.ResponseOrderDetail
import ir.zhiran2021.snamall.feature.profile.order.orderdetail.repo.OrderDetailRepository
import ir.zhiran2021.snamall.feature.profile.orderdelivery.source.OrderDeliveryDataSource

class OrderDeliveryRepositoryImpl(val remoteOrderDeliveryDataSource: OrderDeliveryDataSource):OrderDeliveryRepository {
    override fun getOrderDelivery(): Single<List<ResponseOrderDelivery>> = remoteOrderDeliveryDataSource.getOrderDelivery()
}