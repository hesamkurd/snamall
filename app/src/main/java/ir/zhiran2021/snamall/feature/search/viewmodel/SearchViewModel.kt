package ir.zhiran2021.snamall.feature.search.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseSearch
import ir.zhiran2021.snamall.feature.search.repo.SearchRepository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

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