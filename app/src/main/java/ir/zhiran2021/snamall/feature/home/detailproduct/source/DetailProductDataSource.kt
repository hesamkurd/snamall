package ir.zhiran2021.snamall.feature.home.detailproduct.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseDetailsProduct

interface DetailProductDataSource {
    fun getDetailProduct(productId:Int): Single<ResponseDetailsProduct>
}