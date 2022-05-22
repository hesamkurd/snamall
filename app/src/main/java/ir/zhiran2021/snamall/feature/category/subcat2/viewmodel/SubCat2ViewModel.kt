package ir.zhiran2021.snamall.feature.category.subcat2.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseSubCat2
import ir.zhiran2021.snamall.feature.category.subcat2.repo.SubCat2Repository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

class SubCat2ViewModel(val subCat2_id:Int,val subCat2Repository: SubCat2Repository):BaseViewModel() {

    val subCat2LiveData = MutableLiveData<List<ResponseSubCat2>>()
    val subCat2IdLiveData = MutableLiveData<Int>()

    init {
        subCat2IdLiveData.value = subCat2_id
        progressBarLiveData.value = true
        subCat2Repository.getSubCat2(subCat2IdLiveData.value!!).customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<List<ResponseSubCat2>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseSubCat2>) {
                    subCat2LiveData.value = t
                }
            })
    }

}