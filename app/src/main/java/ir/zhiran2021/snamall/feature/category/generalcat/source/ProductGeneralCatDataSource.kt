package ir.zhiran2021.snamall.feature.category.generalcat.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseProductGeneralCat

interface ProductGeneralCatDataSource {
    fun getProductGeneralCat(general_cat_id:Int):Single<List<ResponseProductGeneralCat>>
}