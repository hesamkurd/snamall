package ir.mamhesam.snamall.feature.cart.nextlevel.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseCheckOutList
import ir.mamhesam.snamall.data.ResponseTransaction

class RemoteCheckOutListDataSource(val apiService: ApiService): CheckOutListDataSource {
    override fun checkOutList(): Single<ResponseCheckOutList> = apiService.checkOutList()
    override fun insertTransaction(
        reciver_id: String,
        shipping_price: String,
        payable_price: String
    ): Single<ResponseTransaction> = apiService.getTransaction(reciver_id, shipping_price, payable_price)
}