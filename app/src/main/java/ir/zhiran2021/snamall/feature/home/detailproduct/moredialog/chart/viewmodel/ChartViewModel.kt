package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.chart.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseChart
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.chart.repo.ChartRepository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

class ChartViewModel(val productId:Int,val chartRepository: ChartRepository):BaseViewModel() {

    val chartLiveData = MutableLiveData<List<ResponseChart>>()
    val productIdLiveData = MutableLiveData<Int>()

    init {
        progressBarLiveData.value = true
        productIdLiveData.value = productId
        chartRepository.getChart(productIdLiveData.value!!).customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<List<ResponseChart>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseChart>) {
                    chartLiveData.value = t
                }
            })
    }
}