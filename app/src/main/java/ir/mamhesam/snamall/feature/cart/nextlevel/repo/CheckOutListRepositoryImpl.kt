package ir.mamhesam.snamall.feature.cart.nextlevel.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseCheckOutList
import ir.mamhesam.snamall.feature.cart.nextlevel.source.CheckOutListDataSource

class CheckOutListRepositoryImpl(val remoteCheckOutListDataSource: CheckOutListDataSource): CheckOutListRepository {
    override fun checkOutList(): Single<ResponseCheckOutList> = remoteCheckOutListDataSource.checkOutList()
}