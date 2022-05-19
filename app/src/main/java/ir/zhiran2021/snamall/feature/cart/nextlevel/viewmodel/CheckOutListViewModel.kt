package ir.zhiran2021.snamall.feature.cart.nextlevel.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseCheckOutList
import ir.zhiran2021.snamall.data.ResponseTransaction
import ir.zhiran2021.snamall.feature.cart.nextlevel.repo.CheckOutListRepository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

class CheckOutListViewModel(val checkOutListRepository: CheckOutListRepository): BaseViewModel() {

    val checkOutListLiveData = MutableLiveData<ResponseCheckOutList>()
    val insertTransactionLiveData = MutableLiveData<ResponseTransaction>()

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
    fun insertTransaction(reciver_id:String,shipping_price:String,payable_price:String){
        progressBarLiveData.value = true
        checkOutListRepository.insertTransaction(reciver_id, shipping_price, payable_price).customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<ResponseTransaction>(compositeDisposable){
                override fun onSuccess(t: ResponseTransaction) {
                    insertTransactionLiveData.value = t
                }

            })
    }
}