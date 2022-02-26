package ir.mamhesam.snamall.feature.home.viewmodel

import androidx.lifecycle.MutableLiveData
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.data.ResponseBanners
import ir.mamhesam.snamall.data.ResponseGeneralCategory
import ir.mamhesam.snamall.feature.home.repo.BannersRepository
import ir.mamhesam.snamall.feature.home.repo.GeneralCategoryRepository
import timber.log.Timber

class HomeViewModel(val bannersRepository: BannersRepository,
                    val generalCategoryRepository: GeneralCategoryRepository): BaseViewModel() {

    val bannersLiveData = MutableLiveData<List<ResponseBanners>>()
    val generalCategoryLiveData = MutableLiveData<List<ResponseGeneralCategory>>()

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
    }
}