package ir.mamhesam.snamall.feature.cart.nextlevel.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.base.customObserver
import ir.mamhesam.snamall.data.ResponseCheckOutList
import ir.mamhesam.snamall.feature.cart.nextlevel.repo.CheckOutListRepository
import ir.mamhesam.snamall.utils.ShopSingleObserver

class CheckOutListViewModel(val checkOutListRepository: CheckOutListRepository): BaseViewModel() {

    val checkOutListLiveData = MutableLiveData<ResponseCheckOutList>()

    init {
        progressBarLiveData.value = true
        checkOutListRepository.checkOutList().customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<ResponseCheckOutList>(compositeDisposable){
                override fun onSuccess(t: ResponseCheckOutList) {
                    checkOutListLiveData.value = t
                }

            })
    }
}