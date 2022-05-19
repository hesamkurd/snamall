package ir.zhiran2021.snamall.feature.profile.order.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseOrderHistory
import ir.zhiran2021.snamall.feature.profile.order.repo.OrderHistoryRepository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

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