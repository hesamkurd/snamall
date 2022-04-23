package ir.mamhesam.snamall.feature.profile.address.repo

import com.google.gson.JsonObject
import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseAddAddress
import ir.mamhesam.snamall.data.ResponseShowAddress

interface AddressRepository {
    fun getAddress():Single<List<ResponseShowAddress>>

    fun addAddress(address: JsonObject): Single<ResponseAddAddress>
}