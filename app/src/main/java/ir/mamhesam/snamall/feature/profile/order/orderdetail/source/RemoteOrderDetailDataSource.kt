package ir.mamhesam.snamall.feature.profile.order.orderdetail.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseOrderDetail

class RemoteOrderDetailDataSource(val apiService: ApiService): OrderDetailDataSoruce {
    override fun getOrderDetail(ref_id: String): Single<ResponseOrderDetail>  = apiService.getOrderDetal(ref_id)
}