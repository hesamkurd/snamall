package ir.mamhesam.snamall.feature.category.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseCategories
import ir.mamhesam.snamall.feature.category.source.CategoriesDataSource

class CategoriesRepositoryImpl(val remoteCategoriesDataSource: CategoriesDataSource): CategoriesRepository {
    override fun getCategories(): Single<List<ResponseCategories>> = remoteCategoriesDataSource.getCategories()
}