package ir.zhiran2021.snamall.feature.home.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseAmazingProducts

class RemoteAmazingProductsDataSource(val apiService: ApiService):AmazingProductsDataSource {
    override fun getAmazingProducts(): Single<List<ResponseAmazingProducts>> = apiService.getAmazingProducts()
}