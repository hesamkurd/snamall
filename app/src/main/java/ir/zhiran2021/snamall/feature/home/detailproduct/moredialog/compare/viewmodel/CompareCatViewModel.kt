package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseCompareCat
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.repo.CompareCatRepository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

class CompareCatViewModel(val productId:Int,val compareCatRepository: CompareCatRepository):BaseViewModel() {
    val compareCatLiveData = MutableLiveData<List<ResponseCompareCat>>()
    val productIdLiveData = MutableLiveData<Int>()

    init {
        progressBarLiveData.value = true
        productIdLiveData.value = productId
        compareCatRepository.getCompareCat(productIdLiveData.value!!).customObserver().doFinally {
            progressBarLiveData.value = false
        }
            .subscribe(object : ShopSingleObserver<List<ResponseCompareCat>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseCompareCat>) {
                    compareCatLiveData.value = t
                }
            })
    }
}