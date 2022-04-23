package ir.mamhesam.snamall.feature.cart.nextlevel.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseCheckOutList

interface CheckOutListRepository {
    fun checkOutList(): Single<ResponseCheckOutList>
}