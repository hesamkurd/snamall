package ir.zhiran2021.snamall.feature.category.subcat1.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseProductSubCat1

interface ProductSubCat1DataSource {
    fun getProductSubCat1(subcat1_id:Int):Single<List<ResponseProductSubCat1>>
}