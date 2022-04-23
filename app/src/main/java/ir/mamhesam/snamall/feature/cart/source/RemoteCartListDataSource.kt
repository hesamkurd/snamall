package ir.mamhesam.snamall.feature.cart.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseCartList
import ir.mamhesam.snamall.data.ResponseChangeCountItem
import ir.mamhesam.snamall.data.ResponseCountCart
import ir.mamhesam.snamall.data.ResponseInsertComment

class RemoteCartListDataSource(val apiService: ApiService): CartListDataSource {
    override fun getCartList(): Single<ResponseCartList> = apiService.getCartList()
    override fun getCountCart(): Single<ResponseCountCart> = apiService.getCountCart()
    override fun removeFromCart(cart_item_id: Int): Single<ResponseInsertComment> = apiService.removeFromCart(cart_item_id)
    override fun changeCountItem(cart_item_id: Int, count: Int): Single<ResponseChangeCountItem> = apiService.changeCountItem(cart_item_id, count)
}