package ir.mamhesam.snamall.feature.home.detailproduct.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseDetailsProduct

interface DetailProductRepository {
    fun getDetailProduct(productId:Int): Single<ResponseDetailsProduct>
}