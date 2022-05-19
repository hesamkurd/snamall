package ir.zhiran2021.snamall.feature.category.subcat.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponsePopularBrand
import ir.zhiran2021.snamall.data.ResponseSubCat1
import ir.zhiran2021.snamall.data.ResponseSubCatProduct

class RemoteSubCatDataSource(val apiService: ApiService): SubCatDataSource {
    override fun getSubCat(sub_id: Int): Single<List<ResponseSubCat1>> = apiService.getSubCat(sub_id)
    override fun popularBrand(sub_id: Int): Single<List<ResponsePopularBrand>> = apiService.getPopularBrand(sub_id)
    override fun subCatProduct(sub_id: Int): Single<List<ResponseSubCatProduct>> = apiService.getSubCatProduct(sub_id)
}