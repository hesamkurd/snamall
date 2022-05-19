package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseCompareProduct
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.repo.CompareProductRepository
import ir.zhiran2021.snamall.utils.ID_ONE
import ir.zhiran2021.snamall.utils.ID_TWO
import ir.zhiran2021.snamall.utils.ShopSingleObserver

class CompareProductViewModel( bundle: Bundle,val compareProductRepository: CompareProductRepository):BaseViewModel() {
    val compareProductLiveData = MutableLiveData<ResponseCompareProduct>()

    var idOne:Int?=null
    var idTwo:Int?=null

    init {
        idOne = bundle.getInt(ID_ONE)
        idTwo = bundle.getInt(ID_TWO)

        progressBarLiveData.value = true
        compareProductRepository.getCompareProduct(idOne!!,idTwo!!).customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<ResponseCompareProduct>(compositeDisposable){
                override fun onSuccess(t: ResponseCompareProduct) {
                    compareProductLiveData.value = t
                }
            })
    }
}