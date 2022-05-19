package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseCompareCat

interface CompareCatDataSource {
    fun getCompareCat(productId:Int):Single<List<ResponseCompareCat>>
}