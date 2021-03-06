package ir.zhiran2021.snamall.feature.home.subcatlevel1.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseSubCatLevel1
import ir.zhiran2021.snamall.feature.home.subcatlevel1.repo.SubCatLevel1Repository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

class SubCatLevel1ViewModel(val generalCatId:Int,val subCatLevel1Repository: SubCatLevel1Repository):BaseViewModel() {

    val subCatLevelLiveData = MutableLiveData<List<ResponseSubCatLevel1>>()
    val generalCatLiveData = MutableLiveData<Int>()
    init {
        generalCatLiveData.value = generalCatId
        progressBarLiveData.value = true
        subCatLevel1Repository.subCatLevel1(generalCatLiveData.value!!)
            .customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<List<ResponseSubCatLevel1>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseSubCatLevel1>) {
                    subCatLevelLiveData.value = t
                }

            })
    }
}