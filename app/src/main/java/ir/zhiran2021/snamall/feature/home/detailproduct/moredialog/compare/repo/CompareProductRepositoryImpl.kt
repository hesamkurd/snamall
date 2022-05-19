package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseCompareProduct
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.source.CompareProductDataSource

class CompareProductRepositoryImpl(val remoteCompareProductDataSource: CompareProductDataSource):CompareProductRepository {
    override fun getCompareProduct(product1: Int, product2: Int): Single<ResponseCompareProduct> = remoteCompareProductDataSource.getCompareProduct(product1, product2)
}