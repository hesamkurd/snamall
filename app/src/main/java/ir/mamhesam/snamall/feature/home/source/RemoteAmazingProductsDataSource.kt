package ir.mamhesam.snamall.feature.home.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseAmazingProducts

class RemoteAmazingProductsDataSource(val apiService: ApiService):AmazingProductsDataSource {
    override fun getAmazingProducts(): Single<List<ResponseAmazingProducts>> = apiService.getAmazingProducts()
}