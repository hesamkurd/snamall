package ir.mamhesam.snamall.feature.category.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseCategories

interface CategoriesRepository {
    fun getCategories(): Single<List<ResponseCategories>>
}