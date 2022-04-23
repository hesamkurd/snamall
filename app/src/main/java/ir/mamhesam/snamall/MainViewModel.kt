package ir.mamhesam.snamall

import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.base.customObserver
import ir.mamhesam.snamall.data.ResponseCountCart
import ir.mamhesam.snamall.feature.cart.repo.CartListRepository
import ir.mamhesam.snamall.feature.profile.auoth.TokenContainer
import ir.mamhesam.snamall.utils.ShopSingleObserver
import org.greenrobot.eventbus.EventBus

class MainViewModel(val cartListRepository: CartListRepository) : BaseViewModel() {


    fun getCount(){
        if (!TokenContainer.token.isNullOrEmpty()) {
            cartListRepository.getCountCart().customObserver()
                .subscribe(object : ShopSingleObserver<ResponseCountCart>(compositeDisposable) {
                    override fun onSuccess(t: ResponseCountCart) {
                        EventBus.getDefault().postSticky(t)
                    }

                })
        }
    }
}