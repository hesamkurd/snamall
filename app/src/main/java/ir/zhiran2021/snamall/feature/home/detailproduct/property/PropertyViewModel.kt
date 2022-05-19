package ir.zhiran2021.snamall.feature.home.detailproduct.property

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseTechnicalProperty
import ir.zhiran2021.snamall.feature.home.detailproduct.property.repo.TechnicalRepository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

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