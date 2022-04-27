package ir.mamhesam.snamall.feature.profile.order.orderdetail.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseOrderDetail
import ir.mamhesam.snamall.feature.profile.order.orderdetail.source.OrderDetailDataSoruce

class OrderDetailRepositoryImpl(val remoteOrderDetailDataSoruce: OrderDetailDataSoruce): OrderDetailRepository {
    override fun getOrderDetail(ref_id: String): Single<ResponseOrderDetail> = remoteOrderDetailDataSoruce.getOrderDetail(ref_id)
}