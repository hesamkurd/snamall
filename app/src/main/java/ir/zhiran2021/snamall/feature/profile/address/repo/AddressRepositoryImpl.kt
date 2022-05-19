package ir.zhiran2021.snamall.feature.profile.address.repo

import com.google.gson.JsonObject
import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseAddAddress
import ir.zhiran2021.snamall.data.ResponseShowAddress
import ir.zhiran2021.snamall.feature.profile.address.source.AddressDataSource

class AddressRepositoryImpl(val remoteAddressDataSource: AddressDataSource): AddressRepository {
    override fun getAddress(): Single<List<ResponseShowAddress>> = remoteAddressDataSource.getAddress()
    override fun addAddress(address: JsonObject): Single<ResponseAddAddress> = remoteAddressDataSource.addAddress(address)
}