package ir.zhiran2021.snamall

import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseCountCart
import ir.zhiran2021.snamall.feature.cart.repo.CartListRepository
import ir.zhiran2021.snamall.feature.profile.auoth.TokenContainer
import ir.zhiran2021.snamall.utils.ShopSingleObserver
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