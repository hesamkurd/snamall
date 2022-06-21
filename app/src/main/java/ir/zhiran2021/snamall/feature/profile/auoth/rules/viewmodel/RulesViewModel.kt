package ir.zhiran2021.snamall.feature.profile.auoth.rules.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseRules
import ir.zhiran2021.snamall.feature.profile.auoth.rules.repo.RulesRepository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

class RulesViewModel(val rulesRepository: RulesRepository):BaseViewModel() {

    val rulesLiveData = MutableLiveData<List<ResponseRules>>()

    init {
        progressBarLiveData.value = true
        rulesRepository.getRules().customObserver()
            .doFinally {
                progressBarLiveData.value =false
            }
            .subscribe(object : ShopSingleObserver<List<ResponseRules>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseRules>) {
                    rulesLiveData.value = t
                }
            })
    }
}