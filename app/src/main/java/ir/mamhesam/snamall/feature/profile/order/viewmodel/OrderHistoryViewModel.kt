package ir.mamhesam.snamall.feature.profile.order.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.base.customObserver
import ir.mamhesam.snamall.data.ResponseOrderHistory
import ir.mamhesam.snamall.feature.profile.order.repo.OrderHistoryRepository
import ir.mamhesam.snamall.utils.ShopSingleObserver

class OrderHistoryViewModel(val orderHistoryRepository: OrderHistoryRepository): BaseViewModel() {

    val orderHistoryLiveData = MutableLiveData<List<ResponseOrderHistory>>()

    init {
        progressBarLiveData.value = true
        orderHistoryRepository.getOrderHistory().customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<List<ResponseOrderHistory>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseOrderHistory>) {
                    orderHistoryLiveData.value = t
                }

            })
    }
}