package ir.zhiran2021.snamall.feature.home.allamazingmarket.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseAllAmazingMarket
import ir.zhiran2021.snamall.feature.home.allamazingmarket.repo.AllAmazingMarketRepository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

class AllAmazingMarketViewModel(var sort:Int,val allAmazingMarketRepository: AllAmazingMarketRepository):BaseViewModel() {

    val allAmazingMarketLiveData = MutableLiveData<List<ResponseAllAmazingMarket>>()
    val selectTitleLiveData = MutableLiveData<Int>()
    val sortTitle = arrayOf(R.string.popular, R.string.lowPrice, R.string.highPrice)


    init {
       getAllAmazingMarket()
        selectTitleLiveData.value = sortTitle[sort]
    }

    fun getAllAmazingMarket(){

        progressBarLiveData.value = true

        allAmazingMarketRepository.getAllAmazingMarket(sort).customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<List<ResponseAllAmazingMarket>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseAllAmazingMarket>) {
                    allAmazingMarketLiveData.value = t
                }
            })
    }
    fun selectedItemDialog(sortId:Int){
        this.sort = sortId
        this.selectTitleLiveData.value = sortTitle[sortId]
        getAllAmazingMarket()
    }
}