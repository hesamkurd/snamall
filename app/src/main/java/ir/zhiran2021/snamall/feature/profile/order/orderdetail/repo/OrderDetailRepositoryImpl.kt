package ir.zhiran2021.snamall.feature.profile.order.orderdetail.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseOrderDetail
import ir.zhiran2021.snamall.feature.profile.order.orderdetail.source.OrderDetailDataSoruce

class OrderDetailRepositoryImpl(val remoteOrderDetailDataSoruce: OrderDetailDataSoruce): OrderDetailRepository {
    override fun getOrderDetail(ref_id: String): Single<ResponseOrderDetail> = remoteOrderDetailDataSoruce.getOrderDetail(ref_id)
}