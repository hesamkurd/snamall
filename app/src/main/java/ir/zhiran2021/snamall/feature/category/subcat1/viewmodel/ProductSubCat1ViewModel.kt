package ir.zhiran2021.snamall.feature.category.subcat1.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseProductSubCat1
import ir.zhiran2021.snamall.feature.category.subcat1.repo.ProductSubCat1Repository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

class ProductSubCat1ViewModel(val subCat1:Int, val productSubCat1Repository: ProductSubCat1Repository):BaseViewModel() {

    val subCat1LiveData = MutableLiveData<Int>()
    val productSubCat1LiveData = MutableLiveData<List<ResponseProductSubCat1>>()

    init {
        subCat1LiveData.value = subCat1
        progressBarLiveData.value = true

        productSubCat1Repository.getProductSubCat1(subCat1LiveData.value!!).customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<List<ResponseProductSubCat1>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseProductSubCat1>) {
                    productSubCat1LiveData.value = t
                }
            })
    }
}