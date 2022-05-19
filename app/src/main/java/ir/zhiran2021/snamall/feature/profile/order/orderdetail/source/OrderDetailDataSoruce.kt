package ir.zhiran2021.snamall.feature.profile.order.orderdetail.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseOrderDetail

interface OrderDetailDataSoruce {
    fun getOrderDetail(ref_id:String): Single<ResponseOrderDetail>
}