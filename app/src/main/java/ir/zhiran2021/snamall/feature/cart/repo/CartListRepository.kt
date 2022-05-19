package ir.zhiran2021.snamall.feature.cart.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseCartList
import ir.zhiran2021.snamall.data.ResponseChangeCountItem
import ir.zhiran2021.snamall.data.ResponseCountCart
import ir.zhiran2021.snamall.data.ResponseInsertComment

interface CartListRepository {
    fun getCartList():Single<ResponseCartList>

    fun getCountCart(): Single<ResponseCountCart>

    fun removeFromCart(cart_item_id:Int):Single<ResponseInsertComment>

    fun changeCountItem(cart_item_id:Int, count:Int): Single<ResponseChangeCountItem>
}