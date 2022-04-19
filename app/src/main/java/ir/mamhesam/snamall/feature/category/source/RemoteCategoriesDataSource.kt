package ir.mamhesam.snamall.feature.category.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseCategories

class RemoteCategoriesDataSource(val apiService: ApiService): CategoriesDataSource {
    override fun getCategories(): Single<List<ResponseCategories>>  = apiService.getCategories()
}