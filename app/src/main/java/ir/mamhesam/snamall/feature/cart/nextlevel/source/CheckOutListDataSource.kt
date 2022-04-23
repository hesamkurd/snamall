package ir.mamhesam.snamall.feature.cart.nextlevel.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseCheckOutList

interface CheckOutListDataSource {
    fun checkOutList(): Single<ResponseCheckOutList>
}