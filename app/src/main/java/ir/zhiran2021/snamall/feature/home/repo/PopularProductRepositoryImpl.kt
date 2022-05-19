package ir.zhiran2021.snamall.feature.home.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponsePopularProduct
import ir.zhiran2021.snamall.feature.home.source.PopularProductDataSource

class PopularProductRepositoryImpl(val remotePopularProductDataSource: PopularProductDataSource): PopularProductRepository {
    override fun getPopularProduct(): Single<List<ResponsePopularProduct>> = remotePopularProductDataSource.getPopularProduct()
}