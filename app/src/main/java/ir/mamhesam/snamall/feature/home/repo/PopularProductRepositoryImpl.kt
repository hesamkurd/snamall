package ir.mamhesam.snamall.feature.home.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponsePopularProduct
import ir.mamhesam.snamall.feature.home.source.PopularProductDataSource

class PopularProductRepositoryImpl(val remotePopularProductDataSource: PopularProductDataSource): PopularProductRepository {
    override fun getPopularProduct(): Single<List<ResponsePopularProduct>> = remotePopularProductDataSource.getPopularProduct()
}