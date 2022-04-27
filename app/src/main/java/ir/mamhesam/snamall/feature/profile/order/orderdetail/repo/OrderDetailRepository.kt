package ir.mamhesam.snamall.feature.profile.order.orderdetail.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseOrderDetail

interface OrderDetailRepository {
    fun getOrderDetail(ref_id:String): Single<ResponseOrderDetail>
}