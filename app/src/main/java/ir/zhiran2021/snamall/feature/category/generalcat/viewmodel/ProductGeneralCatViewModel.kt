package ir.zhiran2021.snamall.feature.category.generalcat.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseProductGeneralCat
import ir.zhiran2021.snamall.feature.category.generalcat.repo.ProductGeneralCatRepository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

class ProductGeneralCatViewModel(val generalCatId:Int,val productGeneralCatRepository: ProductGeneralCatRepository):BaseViewModel() {

    val generalCatIdLiveData = MutableLiveData<Int>()
    val porductGeneralCatLiveData = MutableLiveData<List<ResponseProductGeneralCat>>()

    init {
        generalCatIdLiveData.value = generalCatId
        progressBarLiveData.value = true

        productGeneralCatRepository.getProductGeneralCat(generalCatIdLiveData.value!!).customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<List<ResponseProductGeneralCat>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseProductGeneralCat>) {
                    porductGeneralCatLiveData.value = t
                }
            })
    }
}