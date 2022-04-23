package ir.mamhesam.snamall.feature.profile.address.viewmodel

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.base.customObserver
import ir.mamhesam.snamall.data.ResponseAddAddress
import ir.mamhesam.snamall.data.ResponseShowAddress
import ir.mamhesam.snamall.feature.profile.address.repo.AddressRepository
import ir.mamhesam.snamall.utils.ShopSingleObserver

class AddressViewModel(val addressRepository: AddressRepository):BaseViewModel() {

    val addressLiveData = MutableLiveData<List<ResponseShowAddress>>()
    val addAddressLiveData = MutableLiveData<ResponseAddAddress>()

    init {
       getAddress()
    }
    fun getAddress(){
        progressBarLiveData.value = true
        addressRepository.getAddress().customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<List<ResponseShowAddress>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseShowAddress>) {
                    addressLiveData.value = t
                }

            })
    }

    fun addAddress(address:JsonObject){
        progressBarLiveData.value = true
        addressRepository.addAddress(address).customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<ResponseAddAddress>(compositeDisposable){
                override fun onSuccess(t: ResponseAddAddress) {
                    addAddressLiveData.value = t
                }

            })
    }
}