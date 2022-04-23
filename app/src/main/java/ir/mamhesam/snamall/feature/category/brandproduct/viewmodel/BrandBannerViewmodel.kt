package ir.mamhesam.snamall.feature.category.brandproduct.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.base.customObserver
import ir.mamhesam.snamall.data.ResponseBrandBanner
import ir.mamhesam.snamall.data.ResponseBrandProduct
import ir.mamhesam.snamall.feature.category.brandproduct.repo.BrandBannerRepository
import ir.mamhesam.snamall.utils.ShopSingleObserver

class BrandBannerViewmodel(val brandName:String,val brandBannerRepository: BrandBannerRepository) :BaseViewModel(){

    val brandNameLiveData = MutableLiveData<String>()
    val brandBannerLiveData = MutableLiveData<ResponseBrandBanner>()
    val brandProductLiveData = MutableLiveData<List<ResponseBrandProduct>>()

    init {
        brandNameLiveData.value = brandName
        progressBarLiveData.value = true
        brandBannerRepository.getBrandBanner(brandNameLiveData.value!!).customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<ResponseBrandBanner>(compositeDisposable){
                override fun onSuccess(t: ResponseBrandBanner) {
                    brandBannerLiveData.value = t
                }

            })

        brandBannerRepository.getBrandProduct(brandNameLiveData.value!!).customObserver()
            .subscribe(object : ShopSingleObserver<List<ResponseBrandProduct>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseBrandProduct>) {
                    brandProductLiveData.value = t
                }

            })
    }
}