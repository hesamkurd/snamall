package ir.zhiran2021.snamall.feature.category.subcat.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponsePopularBrand
import ir.zhiran2021.snamall.data.ResponseSubCat1
import ir.zhiran2021.snamall.data.ResponseSubCatProduct

interface SubCatRepository {
    fun getSubCat(sub_id:Int): Single<List<ResponseSubCat1>>

    fun popularBrand(sub_id: Int): Single<List<ResponsePopularBrand>>

    fun subCatProduct(sub_id: Int): Single<List<ResponseSubCatProduct>>
}