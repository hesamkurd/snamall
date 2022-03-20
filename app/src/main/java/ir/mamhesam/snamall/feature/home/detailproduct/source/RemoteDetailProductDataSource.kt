package ir.mamhesam.snamall.feature.home.detailproduct.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseDetailsProduct

class RemoteDetailProductDataSource(val apiService: ApiService): DetailProductDataSource {
    override fun getDetailProduct(productId: Int): Single<ResponseDetailsProduct> = apiService.getDetailProduct(productId)
}