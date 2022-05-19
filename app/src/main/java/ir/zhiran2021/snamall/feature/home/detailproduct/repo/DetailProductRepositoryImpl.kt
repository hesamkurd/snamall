package ir.zhiran2021.snamall.feature.home.detailproduct.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseDetailsProduct
import ir.zhiran2021.snamall.feature.home.detailproduct.source.DetailProductDataSource

class DetailProductRepositoryImpl(val remoteDetailProductDataSource: DetailProductDataSource):DetailProductRepository {
    override fun getDetailProduct(productId: Int): Single<ResponseDetailsProduct> = remoteDetailProductDataSource.getDetailProduct(productId)
}