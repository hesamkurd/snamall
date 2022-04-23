package ir.mamhesam.snamall.feature.cart.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseCartList
import ir.mamhesam.snamall.data.ResponseChangeCountItem
import ir.mamhesam.snamall.data.ResponseCountCart
import ir.mamhesam.snamall.data.ResponseInsertComment
import ir.mamhesam.snamall.feature.cart.source.CartListDataSource

class CartListRepositoryImpl(val remoteCartListDataSource: CartListDataSource): CartListRepository {
    override fun getCartList(): Single<ResponseCartList> = remoteCartListDataSource.getCartList()
    override fun getCountCart(): Single<ResponseCountCart> = remoteCartListDataSource.getCountCart()
    override fun removeFromCart(cart_item_id: Int): Single<ResponseInsertComment> = remoteCartListDataSource.removeFromCart(cart_item_id)
    override fun changeCountItem(cart_item_id: Int, count: Int): Single<ResponseChangeCountItem> = remoteCartListDataSource.changeCountItem(cart_item_id, count)
}