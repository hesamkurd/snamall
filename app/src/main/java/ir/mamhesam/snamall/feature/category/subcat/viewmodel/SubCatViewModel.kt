package ir.mamhesam.snamall.feature.category.subcat.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.base.customObserver
import ir.mamhesam.snamall.data.ResponsePopularBrand
import ir.mamhesam.snamall.data.ResponseSubCat1
import ir.mamhesam.snamall.data.ResponseSubCatProduct
import ir.mamhesam.snamall.feature.category.subcat.repo.SubCatRepository
import ir.mamhesam.snamall.utils.ShopSingleObserver

class SubCatViewModel(val catId:Int,val subCatRepository: SubCatRepository):BaseViewModel() {

    val catIdLiveData = MutableLiveData<Int>()
    val subCatLiveData = MutableLiveData<List<ResponseSubCat1>>()
    val popularBrandLiveData = MutableLiveData<List<ResponsePopularBrand>>()
    val subCatProductLiveData = MutableLiveData<List<ResponseSubCatProduct>>()

    init {
        catIdLiveData.value = catId
        progressBarLiveData.value = true

        subCatRepository.getSubCat(catIdLiveData.value!!).customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<List<ResponseSubCat1>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseSubCat1>) {
                    subCatLiveData.value = t
                }

            })

        subCatRepository.popularBrand(catIdLiveData.value!!).customObserver()
            .subscribe(object : ShopSingleObserver<List<ResponsePopularBrand>>(compositeDisposable){
                override fun onSuccess(t: List<ResponsePopularBrand>) {
                    popularBrandLiveData.value = t
                }

            })

        subCatRepository.subCatProduct(catIdLiveData.value!!).customObserver()
            .subscribe(object : ShopSingleObserver<List<ResponseSubCatProduct>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseSubCatProduct>) {
                    subCatProductLiveData.value = t
                }

            })
    }
}