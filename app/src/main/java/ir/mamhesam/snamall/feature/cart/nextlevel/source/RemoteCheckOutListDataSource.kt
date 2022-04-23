package ir.mamhesam.snamall.feature.cart.nextlevel.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseCheckOutList

class RemoteCheckOutListDataSource(val apiService: ApiService): CheckOutListDataSource {
    override fun checkOutList(): Single<ResponseCheckOutList> = apiService.checkOutList()
}