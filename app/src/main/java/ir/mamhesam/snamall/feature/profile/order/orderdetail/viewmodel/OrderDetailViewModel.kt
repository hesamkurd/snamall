package ir.mamhesam.snamall.feature.profile.order.orderdetail.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.base.customObserver
import ir.mamhesam.snamall.data.ResponseOrderDetail
import ir.mamhesam.snamall.feature.profile.order.orderdetail.repo.OrderDetailRepository
import ir.mamhesam.snamall.utils.ShopSingleObserver

class OrderDetailViewModel(val refId: String,val orderDetailRepository: OrderDetailRepository): BaseViewModel() {

    val orderDetailLiveData = MutableLiveData<ResponseOrderDetail>()
    val refIdLiveData = MutableLiveData<String>()

    init {
        progressBarLiveData.value = true
        refIdLiveData.value = refId

        orderDetailRepository.getOrderDetail(refIdLiveData.value!!).customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<ResponseOrderDetail>(compositeDisposable){
                override fun onSuccess(t: ResponseOrderDetail) {
                    orderDetailLiveData.value = t
                }

            })
    }
}