package ir.mamhesam.snamall.feature.home.allamazing.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.base.customObserver
import ir.mamhesam.snamall.data.ResponseAllAmazing
import ir.mamhesam.snamall.feature.home.allamazing.repo.AllAmazingRepository
import ir.mamhesam.snamall.utils.ShopSingleObserver

class AllAmazingViewModel(var sort:Int,val allAmazingRepository: AllAmazingRepository):BaseViewModel() {
    val allAmazingLiveData = MutableLiveData<List<ResponseAllAmazing>>()
    val selectTitleLiveData = MutableLiveData<Int>()
    val sortTitle = arrayOf(R.string.popular,R.string.lowPrice,R.string.highPrice)

    init {
        getAllAmazing()
        selectTitleLiveData.value = sortTitle[sort]
    }
    fun getAllAmazing(){


        progressBarLiveData.value = true
        allAmazingRepository.allAmazing(sort).customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<List<ResponseAllAmazing>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseAllAmazing>) {
                    allAmazingLiveData.value = t
                }

            })
    }
    fun selectedItemDialog(sortId:Int){
        this.sort = sortId
        this.selectTitleLiveData.value = sortTitle[sortId]
        getAllAmazing()
    }
}