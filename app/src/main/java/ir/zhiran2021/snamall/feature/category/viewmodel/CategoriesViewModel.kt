package ir.zhiran2021.snamall.feature.category.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseCategories
import ir.zhiran2021.snamall.feature.category.repo.CategoriesRepository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

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