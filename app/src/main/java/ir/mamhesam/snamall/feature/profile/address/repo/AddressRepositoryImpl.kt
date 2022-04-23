package ir.mamhesam.snamall.feature.profile.address.repo

import com.google.gson.JsonObject
import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseAddAddress
import ir.mamhesam.snamall.data.ResponseShowAddress
import ir.mamhesam.snamall.feature.profile.address.source.AddressDataSource

class AddressRepositoryImpl(val remoteAddressDataSource: AddressDataSource): AddressRepository {
    override fun getAddress(): Single<List<ResponseShowAddress>> = remoteAddressDataSource.getAddress()
    override fun addAddress(address: JsonObject): Single<ResponseAddAddress> = remoteAddressDataSource.addAddress(address)
}