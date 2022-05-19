package ir.zhiran2021.snamall.feature.category.brandproduct.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseBrandBanner
import ir.zhiran2021.snamall.data.ResponseBrandProduct
import ir.zhiran2021.snamall.feature.category.brandproduct.repo.BrandBannerRepository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

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