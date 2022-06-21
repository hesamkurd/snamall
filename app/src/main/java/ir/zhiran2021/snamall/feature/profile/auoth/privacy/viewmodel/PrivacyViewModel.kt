package ir.zhiran2021.snamall.feature.profile.auoth.privacy.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponsePrivacy
import ir.zhiran2021.snamall.feature.profile.auoth.privacy.repo.PrivacyRepository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

class PrivacyViewModel(val privacyRepository: PrivacyRepository):BaseViewModel() {

    val privacyLivaData = MutableLiveData<List<ResponsePrivacy>>()

    init {
        progressBarLiveData.value = true
        privacyRepository.getPrivacy().customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<List<ResponsePrivacy>>(compositeDisposable){
                override fun onSuccess(t: List<ResponsePrivacy>) {
                    privacyLivaData.value= t
                }
            })
    }
}