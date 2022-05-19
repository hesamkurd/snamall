package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseCompareCat

interface CompareCatRepository {
    fun getCompareCat(productId:Int):Single<List<ResponseCompareCat>>
}