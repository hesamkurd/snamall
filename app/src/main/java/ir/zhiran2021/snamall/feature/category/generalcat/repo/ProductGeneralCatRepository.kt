package ir.zhiran2021.snamall.feature.category.generalcat.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseProductGeneralCat

interface ProductGeneralCatRepository {
    fun getProductGeneralCat(general_cat_id:Int):Single<List<ResponseProductGeneralCat>>
}