package ir.zhiran2021.snamall.feature.category.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseCategories
import ir.zhiran2021.snamall.feature.category.source.CategoriesDataSource

class CategoriesRepositoryImpl(val remoteCategoriesDataSource: CategoriesDataSource): CategoriesRepository {
    override fun getCategories(): Single<List<ResponseCategories>> = remoteCategoriesDataSource.getCategories()
}