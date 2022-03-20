package ir.mamhesam.snamall.feature.home.detailproduct.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseDetailsProduct

interface DetailProductDataSource {
    fun getDetailProduct(productId:Int): Single<ResponseDetailsProduct>
}