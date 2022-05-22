package ir.zhiran2021.snamall.feature.profile.orderdelivery.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseOrderDelivery

class RemoteOrderDeliveryDataSource(val apiService: ApiService):OrderDeliveryDataSource {
    override fun getOrderDelivery(): Single<List<ResponseOrderDelivery>> = apiService.getOrderDelivery()
}