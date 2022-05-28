package ir.zhiran2021.snamall.feature.home.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.*
import ir.zhiran2021.snamall.feature.home.repo.*
import ir.zhiran2021.snamall.utils.ShopSingleObserver
import timber.log.Timber

class HomeViewModel(val bannersRepository: BannersRepository,
                    val generalCategoryRepository: GeneralCategoryRepository,
                    val amazingProductsRepository: AmazingProductsRepository,
                    val popularProductRepository: PopularProductRepository,
                    val bannerType2Repository: BannerType2Repository,
                    val bestSellRepository: BestSellRepository,
                    val amazingMarketRepository: AmazingMarketRepository): BaseViewModel() {

    val bannersLiveData = MutableLiveData<List<ResponseBanners>>()
    val generalCategoryLiveData = MutableLiveData<List<ResponseGeneralCategory>>()
    val amazingProductsLiveData = MutableLiveData<List<ResponseAmazingProducts>>()
    val popularProductLiveData = MutableLiveData<List<ResponsePopularProduct>>()
    val bannerType2LiveData = MutableLiveData<List<ResponseBannerType2>>()
    val bestSellLiveData = MutableLiveData<List<ResponseBestSellProduct>>()
    val amazingMarketLiveData = MutableLiveData<List<ResponseAmazingMarket>>()

    init {

        progressBarLiveData.value = true

        bannersRepository.getBanners().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                progressBarLiveData.value = false

            }
            .subscribe(object : SingleObserver<List<ResponseBanners>>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<ResponseBanners>) {
                    bannersLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                }

            })

        generalCategoryRepository.getGeneralCategory().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<ResponseGeneralCategory>>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<ResponseGeneralCategory>) {
                    generalCategoryLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                }

            })
        amazingProductsRepository.getAmazingProducts().customObserver()
            .subscribe(object : ShopSingleObserver<List<ResponseAmazingProducts>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseAmazingProducts>) {
                    amazingProductsLiveData.value = t
                }

            })
        popularProductRepository.getPopularProduct().customObserver()
            .subscribe(object : ShopSingleObserver<List<ResponsePopularProduct>>(compositeDisposable){
                override fun onSuccess(t: List<ResponsePopularProduct>) {
                    popularProductLiveData.value = t
                }

            })

        bannerType2Repository.getBannerType2().customObserver()
            .subscribe(object : ShopSingleObserver<List<ResponseBannerType2>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseBannerType2>) {
                    bannerType2LiveData.value = t
                }

            })

        bestSellRepository.getBestSell().customObserver()
            .subscribe(object : ShopSingleObserver<List<ResponseBestSellProduct>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseBestSellProduct>) {
                    bestSellLiveData.value = t
                }
            })

        amazingMarketRepository.getAmazingMarket().customObserver()
            .subscribe(object : ShopSingleObserver<List<ResponseAmazingMarket>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseAmazingMarket>) {
                    amazingMarketLiveData.value = t
                }
            })
    }

}