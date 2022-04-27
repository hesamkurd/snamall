package ir.mamhesam.snamall.feature.profile.infouser.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.base.customObserver
import ir.mamhesam.snamall.data.ResponseInfoUser
import ir.mamhesam.snamall.feature.profile.infouser.repo.InfoUserRepository
import ir.mamhesam.snamall.utils.ShopSingleObserver

class InfoUserViewModel(val infoUserRepository: InfoUserRepository):BaseViewModel() {

    val infoUserLiveData = MutableLiveData<ResponseInfoUser>()

    init {
        progressBarLiveData.value = true
        infoUserRepository.getInfoUser().customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<ResponseInfoUser>(compositeDisposable){
                override fun onSuccess(t: ResponseInfoUser) {
                    infoUserLiveData.value = t
                }

            })
    }
}