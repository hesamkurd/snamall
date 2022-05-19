package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseCompareProduct

interface CompareProductRepository {
    fun getCompareProduct(product1:Int,product2:Int):Single<ResponseCompareProduct>
}