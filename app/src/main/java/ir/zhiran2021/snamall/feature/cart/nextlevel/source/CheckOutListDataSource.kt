package ir.zhiran2021.snamall.feature.cart.nextlevel.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseCheckOutList
import ir.zhiran2021.snamall.data.ResponseTransaction

interface CheckOutListDataSource {
    fun checkOutList(): Single<ResponseCheckOutList>
    fun insertTransaction(reciver_id:String,shipping_price:String,payable_price:String): Single<ResponseTransaction>

}