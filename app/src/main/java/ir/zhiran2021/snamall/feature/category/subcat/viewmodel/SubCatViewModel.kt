package ir.zhiran2021.snamall.feature.category.subcat.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponsePopularBrand
import ir.zhiran2021.snamall.data.ResponseSubCat1
import ir.zhiran2021.snamall.data.ResponseSubCatProduct
import ir.zhiran2021.snamall.feature.category.subcat.repo.SubCatRepository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

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