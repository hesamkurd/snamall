package ir.zhiran2021.snamall.feature.category.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseCategories

class RemoteCategoriesDataSource(val apiService: ApiService): CategoriesDataSource {
    override fun getCategories(): Single<List<ResponseCategories>>  = apiService.getCategories()
}