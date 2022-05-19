package ir.zhiran2021.snamall.feature.home.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseAmazingProducts
import ir.zhiran2021.snamall.feature.home.source.AmazingProductsDataSource

class AmazingProductsRepositoryImpl(val remoteAmazingProductsDataSource: AmazingProductsDataSource):AmazingProductsRepository{
    override fun getAmazingProducts(): Single<List<ResponseAmazingProducts>> = remoteAmazingProductsDataSource.getAmazingProducts()
}