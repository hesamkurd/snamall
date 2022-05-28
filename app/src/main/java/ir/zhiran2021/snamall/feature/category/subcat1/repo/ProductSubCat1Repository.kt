package ir.zhiran2021.snamall.feature.category.subcat1.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseProductSubCat1

interface ProductSubCat1Repository {
    fun getProductSubCat1(subcat1_id:Int):Single<List<ResponseProductSubCat1>>
}