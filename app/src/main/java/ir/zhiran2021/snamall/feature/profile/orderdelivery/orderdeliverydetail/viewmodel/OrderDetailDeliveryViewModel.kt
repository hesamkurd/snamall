package ir.zhiran2021.snamall.feature.profile.orderdelivery.orderdeliverydetail.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseOrderDetailDelivery
import ir.zhiran2021.snamall.feature.profile.orderdelivery.orderdeliverydetail.repo.OrderDetailDeliveryRepository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

class OrderDetailDeliveryViewModel(val refId:String,val orderDetailDeliveryRepository: OrderDetailDeliveryRepository):BaseViewModel() {

    val orderDetailDeliveryLiveData = MutableLiveData<ResponseOrderDetailDelivery>()
    val refIdLiveData = MutableLiveData<String>()

    init {
        refIdLiveData.value = refId
        progressBarLiveData.value = true

        orderDetailDeliveryRepository.getOrderDetailDelivery(refIdLiveData.value!!).customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<ResponseOrderDetailDelivery>(compositeDisposable){
                override fun onSuccess(t: ResponseOrderDetailDelivery) {
                    orderDetailDeliveryLiveData.value = t
                }
            })
    }
}