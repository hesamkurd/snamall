package ir.mamhesam.snamall.feature.home.viewmodel

import androidx.lifecycle.MutableLiveData
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.base.customObserver
import ir.mamhesam.snamall.data.ResponseAmazingProducts
import ir.mamhesam.snamall.data.ResponseBanners
import ir.mamhesam.snamall.data.ResponseGeneralCategory
import ir.mamhesam.snamall.data.ResponsePopularProduct
import ir.mamhesam.snamall.feature.home.repo.AmazingProductsRepository
import ir.mamhesam.snamall.feature.home.repo.BannersRepository
import ir.mamhesam.snamall.feature.home.repo.GeneralCategoryRepository
import ir.mamhesam.snamall.feature.home.repo.PopularProductRepository
import ir.mamhesam.snamall.utils.ShopSingleObserver
import timber.log.Timber

class HomeViewModel(val bannersRepository: BannersRepository,
                    val generalCategoryRepository: GeneralCategoryRepository,
                    val amazingProductsRepository: AmazingProductsRepository,
                    val popularProductRepository: PopularProductRepository): BaseViewModel() {

    val bannersLiveData = MutableLiveData<List<ResponseBanners>>()
    val generalCategoryLiveData = MutableLiveData<List<ResponseGeneralCategory>>()
    val amazingProductsLiveData = MutableLiveData<List<ResponseAmazingProducts>>()
    val popularProductLiveData = MutableLiveData<List<ResponsePopularProduct>>()

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
    }

}