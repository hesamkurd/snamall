package ir.zhiran2021.snamall.feature.profile.infouser.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseInfoUser
import ir.zhiran2021.snamall.feature.profile.infouser.repo.InfoUserRepository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

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