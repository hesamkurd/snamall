package ir.mamhesam.snamall.feature.cart.viewmodel

import androidx.lifecycle.MutableLiveData
import io.reactivex.Completable
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.base.customObserver
import ir.mamhesam.snamall.data.EmptyState
import ir.mamhesam.snamall.data.ProductItemItem
import ir.mamhesam.snamall.data.ResponseCartList
import ir.mamhesam.snamall.data.ResponseCountCart
import ir.mamhesam.snamall.feature.cart.repo.CartListRepository
import ir.mamhesam.snamall.feature.profile.auoth.TokenContainer
import ir.mamhesam.snamall.utils.ShopSingleObserver
import org.greenrobot.eventbus.EventBus

class CartListViewModel(val cartListRepository: CartListRepository): BaseViewModel() {
    val cartListLiveData = MutableLiveData<ResponseCartList>()
    val emptyStateLiveData = MutableLiveData<EmptyState>()
    val payablePriceLiveData = MutableLiveData<Int>()
    val totalPriceLiveData = MutableLiveData<Int>()
    val totalOfPriceLiveData = MutableLiveData<Int>()

    init {
        getListCart()
    }

    fun getListCart(){
        if (!TokenContainer.token.isNullOrEmpty()){
            progressBarLiveData.value = true
            emptyStateLiveData.value = EmptyState(false)
            cartListRepository.getCartList().customObserver()
                .doFinally {
                    progressBarLiveData.value = false
                }
                .subscribe(object : ShopSingleObserver<ResponseCartList>(compositeDisposable){
                    override fun onSuccess(t: ResponseCartList) {
                        if (!t.productItem.isNullOrEmpty()){
                            cartListLiveData.value = t
                            payablePriceLiveData.value = t.payablePrice
                            totalPriceLiveData.value = t.totalPrice
                            totalOfPriceLiveData.value = t.totalOffPrice

                        }else{
                            emptyStateLiveData.value = EmptyState(true,R.string.empty_cart)
                        }
                    }

                })
        }else{
            emptyStateLiveData.value = EmptyState(true,R.string.isLoginCart,true)
        }
    }

    fun removeFromCart(cartItem : ProductItemItem):Completable{

        return cartListRepository.removeFromCart(cartItem.id.toInt()).doAfterSuccess {
            val cartItems: MutableList<ProductItemItem> = cartListLiveData.value!!.productItem as MutableList<ProductItemItem>
            val index = cartItems.indexOf(cartItem)
            cartItems.removeAt(index)
            cartListLiveData.value!!.productItem = cartItems

            val countItem = EventBus.getDefault().getStickyEvent(ResponseCountCart::class.java)
            countItem?.let {
                it.count -= cartItem.count.toInt()
                EventBus.getDefault().postSticky(it)
            }

            changePrice()
            cartListLiveData?.let {
                if (it.value!!.productItem.isEmpty()){
                    emptyStateLiveData.postValue(EmptyState(true,R.string.emptyCart))
                }
            }
        }.ignoreElement()
    }

    fun changePrice(){
        cartListLiveData.value?.let {recponce->
            totalPriceLiveData.value?.let {

                var totalPrice = 0
                var ofPrice = 0
                var payablePrice = 0

                recponce.productItem.forEach {cartItem->

                    totalPrice += cartItem.price.toInt() * cartItem.count.toInt()
                    ofPrice += cartItem.offPrice.toInt() * cartItem.count.toInt()
                    payablePrice = totalPrice - ofPrice
                }
                totalPriceLiveData.postValue(totalPrice)
                totalOfPriceLiveData.postValue(ofPrice)
                payablePriceLiveData.postValue(payablePrice)
            }
        }
    }

    fun changeCountIte(cartItem: ProductItemItem, count: Int, isChange: Boolean): Completable{

        return cartListRepository.changeCountItem(cartItem.id.toInt(), count).doAfterSuccess {

            val cartItemCount = EventBus.getDefault().getStickyEvent(ResponseCountCart::class.java)
            cartItemCount?.let {
                if (isChange){
                    it.count += 1
                    EventBus.getDefault().postSticky(it)
                }else{
                    it.count -=1
                    EventBus.getDefault().postSticky(it)
                }

            }
            val cartItems: MutableList<ProductItemItem> = cartListLiveData.value!!.productItem as MutableList<ProductItemItem>
            val index = cartItems.indexOf(cartItem)
            cartItem.count = count.toString()
            cartItems.set(index,cartItem)
            cartListLiveData.value!!.productItem = cartItems
            changePrice()

        }.ignoreElement()
    }

    fun refresh(){
        getListCart()
    }


}