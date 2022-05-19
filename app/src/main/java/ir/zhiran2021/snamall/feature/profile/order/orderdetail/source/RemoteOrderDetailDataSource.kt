package ir.zhiran2021.snamall.feature.profile.order.orderdetail.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseOrderDetail

class RemoteOrderDetailDataSource(val apiService: ApiService): OrderDetailDataSoruce {
    override fun getOrderDetail(ref_id: String): Single<ResponseOrderDetail>  = apiService.getOrderDetal(ref_id)
}