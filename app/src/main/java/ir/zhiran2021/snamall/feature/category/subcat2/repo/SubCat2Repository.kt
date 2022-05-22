package ir.zhiran2021.snamall.feature.category.subcat2.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseSubCat2

interface SubCat2Repository {
    fun getSubCat2(subcat2_id:Int): Single<List<ResponseSubCat2>>
}