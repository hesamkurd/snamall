package ir.mamhesam.snamall.feature.home.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseAmazingProducts
import ir.mamhesam.snamall.feature.home.source.AmazingProductsDataSource

class AmazingProductsRepositoryImpl(val remoteAmazingProductsDataSource: AmazingProductsDataSource):AmazingProductsRepository{
    override fun getAmazingProducts(): Single<List<ResponseAmazingProducts>> = remoteAmazingProductsDataSource.getAmazingProducts()
}