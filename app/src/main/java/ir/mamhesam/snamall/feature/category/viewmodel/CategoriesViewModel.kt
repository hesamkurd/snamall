package ir.mamhesam.snamall.feature.category.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.base.customObserver
import ir.mamhesam.snamall.data.ResponseCategories
import ir.mamhesam.snamall.feature.category.repo.CategoriesRepository
import ir.mamhesam.snamall.utils.ShopSingleObserver

class CategoriesViewModel(val categoriesRepository: CategoriesRepository):BaseViewModel() {

    val categoriesLiveData= MutableLiveData<List<ResponseCategories>>()
    init {
        progressBarLiveData.value = true
        categoriesRepository.getCategories().customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<List<ResponseCategories>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseCategories>) {
                    categoriesLiveData.value = t
                }

            })
    }
}