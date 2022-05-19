package ir.zhiran2021.snamall.feature.profile.order.orderdetail.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseOrderDetail

interface OrderDetailRepository {
    fun getOrderDetail(ref_id:String): Single<ResponseOrderDetail>
}