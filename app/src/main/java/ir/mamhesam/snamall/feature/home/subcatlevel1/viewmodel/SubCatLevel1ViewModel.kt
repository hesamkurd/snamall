package ir.mamhesam.snamall.feature.home.subcatlevel1.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.base.customObserver
import ir.mamhesam.snamall.data.ResponseSubCatLevel1
import ir.mamhesam.snamall.feature.home.subcatlevel1.repo.SubCatLevel1Repository
import ir.mamhesam.snamall.utils.ShopSingleObserver

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