package ir.mamhesam.snamall.feature.category.subcat.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponsePopularBrand
import ir.mamhesam.snamall.data.ResponseSubCat1
import ir.mamhesam.snamall.data.ResponseSubCatProduct

interface SubCatRepository {
    fun getSubCat(sub_id:Int): Single<List<ResponseSubCat1>>

    fun popularBrand(sub_id: Int): Single<List<ResponsePopularBrand>>

    fun subCatProduct(sub_id: Int): Single<List<ResponseSubCatProduct>>
}