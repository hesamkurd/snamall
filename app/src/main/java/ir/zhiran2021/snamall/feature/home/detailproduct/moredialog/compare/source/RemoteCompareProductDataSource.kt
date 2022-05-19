package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseCompareProduct

class RemoteCompareProductDataSource(val apiService: ApiService):CompareProductDataSource {
    override fun getCompareProduct(product1: Int, product2: Int): Single<ResponseCompareProduct> = apiService.getCompareProduct(product1, product2)
}