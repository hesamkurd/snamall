package ir.zhiran2021.snamall.feature.home.detailproduct.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseDetailsProduct

interface DetailProductRepository {
    fun getDetailProduct(productId:Int): Single<ResponseDetailsProduct>
}