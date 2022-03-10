package ir.mamhesam.snamall.feature.home.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponsePopularProduct

class RemotePopularProductDataSource(val apiService: ApiService): PopularProductDataSource {
    override fun getPopularProduct(): Single<List<ResponsePopularProduct>> = apiService.getPopularProduct()
}