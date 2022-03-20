package ir.mamhesam.snamall.feature.home.detailproduct.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.base.customObserver
import ir.mamhesam.snamall.data.ResponseDetailsProduct
import ir.mamhesam.snamall.feature.home.detailproduct.repo.DetailProductRepository
import ir.mamhesam.snamall.utils.ShopSingleObserver

class DetailProductViewModel(val product_id: Int,val detailProductRepository: DetailProductRepository): BaseViewModel() {

    val detailProductLiveData = MutableLiveData<ResponseDetailsProduct>()
    val productIdLiveData = MutableLiveData<Int>()

    init {
        productIdLiveData.value = product_id
        progressBarLiveData.value = true
        detailProductRepository.getDetailProduct(productIdLiveData.value!!).customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<ResponseDetailsProduct>(compositeDisposable){
                override fun onSuccess(t: ResponseDetailsProduct) {
                    detailProductLiveData.value = t
                }

            })
    }
}