package ir.zhiran2021.snamall.feature.home.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponsePopularProduct

class RemotePopularProductDataSource(val apiService: ApiService): PopularProductDataSource {
    override fun getPopularProduct(): Single<List<ResponsePopularProduct>> = apiService.getPopularProduct()
}