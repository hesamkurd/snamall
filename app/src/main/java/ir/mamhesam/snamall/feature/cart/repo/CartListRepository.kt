package ir.mamhesam.snamall.feature.cart.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseCartList
import ir.mamhesam.snamall.data.ResponseChangeCountItem
import ir.mamhesam.snamall.data.ResponseCountCart
import ir.mamhesam.snamall.data.ResponseInsertComment

interface CartListRepository {
    fun getCartList():Single<ResponseCartList>

    fun getCountCart(): Single<ResponseCountCart>

    fun removeFromCart(cart_item_id:Int):Single<ResponseInsertComment>

    fun changeCountItem(cart_item_id:Int, count:Int): Single<ResponseChangeCountItem>
}