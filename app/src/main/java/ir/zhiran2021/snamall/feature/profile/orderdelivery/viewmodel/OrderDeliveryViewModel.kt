package ir.zhiran2021.snamall.feature.profile.orderdelivery.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseOrderDelivery
import ir.zhiran2021.snamall.feature.profile.orderdelivery.repo.OrderDeliveryRepository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

class OrderDeliveryViewModel(val orderDeliveryRepository: OrderDeliveryRepository):BaseViewModel() {

    val orderDeliveryLiveData = MutableLiveData<List<ResponseOrderDelivery>>()

    init {
        progressBarLiveData.value = true
        orderDeliveryRepository.getOrderDelivery().customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<List<ResponseOrderDelivery>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseOrderDelivery>) {
                    orderDeliveryLiveData.value = t
                }
            })
    }
}