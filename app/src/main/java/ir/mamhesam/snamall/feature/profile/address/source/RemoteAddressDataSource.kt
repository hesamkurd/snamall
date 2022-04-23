package ir.mamhesam.snamall.feature.profile.address.source

import com.google.gson.JsonObject
import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseAddAddress
import ir.mamhesam.snamall.data.ResponseShowAddress

class RemoteAddressDataSource(val apiService: ApiService): AddressDataSource {
    override fun getAddress(): Single<List<ResponseShowAddress>> = apiService.getAddress()
    override fun addAddress(address: JsonObject): Single<ResponseAddAddress> = apiService.addAddress(address)
}