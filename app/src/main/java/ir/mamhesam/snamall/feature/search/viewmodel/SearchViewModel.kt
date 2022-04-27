package ir.mamhesam.snamall.feature.search.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.base.customObserver
import ir.mamhesam.snamall.data.ResponseSearch
import ir.mamhesam.snamall.feature.search.repo.SearchRepository
import ir.mamhesam.snamall.utils.ShopSingleObserver

class SearchViewModel(val searchRepository: SearchRepository):BaseViewModel() {

    val searchLiveData = MutableLiveData<ResponseSearch>()

    fun getSearch(search:String){
        progressBarLiveData.value = true
        searchRepository.getSearch(search).customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<ResponseSearch>(compositeDisposable){
                override fun onSuccess(t: ResponseSearch) {
                    searchLiveData.value = t
                }
            })
    }
}