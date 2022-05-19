package ir.zhiran2021.snamall.feature.category.subcat.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponsePopularBrand
import ir.zhiran2021.snamall.data.ResponseSubCat1
import ir.zhiran2021.snamall.data.ResponseSubCatProduct

interface SubCatDataSource {
    fun getSubCat(sub_id:Int): Single<List<ResponseSubCat1>>

    fun popularBrand(sub_id: Int): Single<List<ResponsePopularBrand>>

    fun subCatProduct(sub_id: Int): Single<List<ResponseSubCatProduct>>


}