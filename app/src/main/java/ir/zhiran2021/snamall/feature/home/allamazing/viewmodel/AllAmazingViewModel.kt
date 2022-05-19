package ir.zhiran2021.snamall.feature.home.allamazing.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseAllAmazing
import ir.zhiran2021.snamall.feature.home.allamazing.repo.AllAmazingRepository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

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