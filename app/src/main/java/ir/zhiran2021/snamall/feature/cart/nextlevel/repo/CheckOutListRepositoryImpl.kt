package ir.zhiran2021.snamall.feature.cart.nextlevel.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseCheckOutList
import ir.zhiran2021.snamall.data.ResponseTransaction
import ir.zhiran2021.snamall.feature.cart.nextlevel.source.CheckOutListDataSource

class CheckOutListRepositoryImpl(val remoteCheckOutListDataSource: CheckOutListDataSource): CheckOutListRepository {
    override fun checkOutList(): Single<ResponseCheckOutList> = remoteCheckOutListDataSource.checkOutList()
    override fun insertTransaction(
        reciver_id: String,
        shipping_price: String,
        payable_price: String
    ): Single<ResponseTransaction> = remoteCheckOutListDataSource.insertTransaction(reciver_id, shipping_price, payable_price)
}