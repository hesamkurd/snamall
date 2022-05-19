package ir.zhiran2021.snamall.feature.profile.address.source

import com.google.gson.JsonObject
import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseAddAddress
import ir.zhiran2021.snamall.data.ResponseShowAddress

interface AddressDataSource {
    fun getAddress():Single<List<ResponseShowAddress>>

    fun addAddress(address: JsonObject): Single<ResponseAddAddress>

}