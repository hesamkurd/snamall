package ir.mamhesam.snamall.feature.category.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseCategories

interface CategoriesDataSource {
    fun getCategories(): Single<List<ResponseCategories>>
}