package ir.zhiran2021.snamall.feature.category.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseCategories

interface CategoriesRepository {
    fun getCategories(): Single<List<ResponseCategories>>
}