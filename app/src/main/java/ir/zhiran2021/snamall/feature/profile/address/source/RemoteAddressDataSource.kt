package ir.zhiran2021.snamall.feature.profile.address.source

import com.google.gson.JsonObject
import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseAddAddress
import ir.zhiran2021.snamall.data.ResponseShowAddress

class RemoteAddressDataSource(val apiService: ApiService): AddressDataSource {
    override fun getAddress(): Single<List<ResponseShowAddress>> = apiService.getAddress()
    override fun addAddress(address: JsonObject): Single<ResponseAddAddress> = apiService.addAddress(address)
}