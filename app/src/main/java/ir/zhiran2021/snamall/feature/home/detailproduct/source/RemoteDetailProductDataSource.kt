package ir.zhiran2021.snamall.feature.home.detailproduct.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseDetailsProduct

class RemoteDetailProductDataSource(val apiService: ApiService): DetailProductDataSource {
    override fun getDetailProduct(productId: Int): Single<ResponseDetailsProduct> = apiService.getDetailProduct(productId)
}