package ir.zhiran2021.snamall.feature.category.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseCategories

interface CategoriesDataSource {
    fun getCategories(): Single<List<ResponseCategories>>
}