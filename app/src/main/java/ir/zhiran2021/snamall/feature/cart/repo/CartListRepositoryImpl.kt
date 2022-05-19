package ir.zhiran2021.snamall.feature.cart.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseCartList
import ir.zhiran2021.snamall.data.ResponseChangeCountItem
import ir.zhiran2021.snamall.data.ResponseCountCart
import ir.zhiran2021.snamall.data.ResponseInsertComment
import ir.zhiran2021.snamall.feature.cart.source.CartListDataSource

class CartListRepositoryImpl(val remoteCartListDataSource: CartListDataSource): CartListRepository {
    override fun getCartList(): Single<ResponseCartList> = remoteCartListDataSource.getCartList()
    override fun getCountCart(): Single<ResponseCountCart> = remoteCartListDataSource.getCountCart()
    override fun removeFromCart(cart_item_id: Int): Single<ResponseInsertComment> = remoteCartListDataSource.removeFromCart(cart_item_id)
    override fun changeCountItem(cart_item_id: Int, count: Int): Single<ResponseChangeCountItem> = remoteCartListDataSource.changeCountItem(cart_item_id, count)
}