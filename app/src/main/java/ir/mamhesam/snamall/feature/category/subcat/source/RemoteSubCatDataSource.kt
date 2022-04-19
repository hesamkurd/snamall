package ir.mamhesam.snamall.feature.category.subcat.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponsePopularBrand
import ir.mamhesam.snamall.data.ResponseSubCat1
import ir.mamhesam.snamall.data.ResponseSubCatProduct

class RemoteSubCatDataSource(val apiService: ApiService): SubCatDataSource {
    override fun getSubCat(sub_id: Int): Single<List<ResponseSubCat1>> = apiService.getSubCat(sub_id)
    override fun popularBrand(sub_id: Int): Single<List<ResponsePopularBrand>> = apiService.getPopularBrand(sub_id)
    override fun subCatProduct(sub_id: Int): Single<List<ResponseSubCatProduct>> = apiService.getSubCatProduct(sub_id)
}