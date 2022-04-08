package ir.mamhesam.snamall.feature.home.detailproduct.property

import androidx.lifecycle.MutableLiveData
import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.base.customObserver
import ir.mamhesam.snamall.data.ResponseTechnicalProperty
import ir.mamhesam.snamall.feature.home.detailproduct.property.repo.TechnicalRepository
import ir.mamhesam.snamall.utils.ShopSingleObserver

class PropertyViewModel(val productId:Int, val technicalRepository: TechnicalRepository): BaseViewModel() {

    val technicalPropertyLiveData = MutableLiveData<List<ResponseTechnicalProperty>>()
    val productIdLiveData = MutableLiveData<Int>()

    init {

        productIdLiveData.value = productId
        progressBarLiveData.value = true
        technicalRepository.getTechnical(productIdLiveData.value!!).customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object: ShopSingleObserver<List<ResponseTechnicalProperty>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseTechnicalProperty>) {
                    technicalPropertyLiveData.value = t
                }

            })
    }
}