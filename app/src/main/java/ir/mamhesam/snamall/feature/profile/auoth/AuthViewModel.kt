package ir.mamhesam.snamall.feature.profile.auoth

import androidx.lifecycle.MutableLiveData
import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.base.customObserver
import ir.mamhesam.snamall.data.ResponseAddFavorite
import ir.mamhesam.snamall.data.ResponseCheckUser
import ir.mamhesam.snamall.data.ResponseLogin
import ir.mamhesam.snamall.data.ResponseRegister
import ir.mamhesam.snamall.feature.profile.auoth.repo.AuthRepository
import ir.mamhesam.snamall.utils.ShopSingleObserver

class AuthViewModel(val authRepository: AuthRepository): BaseViewModel() {

    val checkUserLiveData= MutableLiveData<ResponseCheckUser>()
    val registerLiveData = MutableLiveData<ResponseRegister>()
    val loginLiveData = MutableLiveData<ResponseLogin>()
    val checkLoginLiveData = MutableLiveData<Boolean>()

    val addToFavoriteLiveData = MutableLiveData<ResponseAddFavorite>()

    fun checkUser(phone: String){
        authRepository.checkUser(phone).customObserver()
            .subscribe(object : ShopSingleObserver<ResponseCheckUser>(compositeDisposable){
                override fun onSuccess(t: ResponseCheckUser) {
                    checkUserLiveData.value = t
                }

            })
    }

    fun register(phone: String?, name: String?){
        authRepository.register(phone!!, name!!).customObserver()
            .subscribe(object : ShopSingleObserver<ResponseRegister>(compositeDisposable){
                override fun onSuccess(t: ResponseRegister) {
                    registerLiveData.value = t
                }

            })
    }

    fun login(phone: String?){
        authRepository.login(phone!!).customObserver()
            .subscribe(object : ShopSingleObserver<ResponseLogin>(compositeDisposable){
                override fun onSuccess(t: ResponseLogin) {
                    loginLiveData.value = t
                }

            })
    }

    fun checkLogin(){
        checkLoginLiveData.value = authRepository.checkLogin()
    }

    fun addToFavorite(productId: Int){
        authRepository.addToFavorite(productId).customObserver()
            .subscribe(object : ShopSingleObserver<ResponseAddFavorite>(compositeDisposable){
                override fun onSuccess(t: ResponseAddFavorite) {
                    addToFavoriteLiveData.value = t
                }

            })
    }


}