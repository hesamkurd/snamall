package ir.mamhesam.snamall.feature.profile.order.orderdetail.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseOrderDetail

interface OrderDetailDataSoruce {
    fun getOrderDetail(ref_id:String): Single<ResponseOrderDetail>
}