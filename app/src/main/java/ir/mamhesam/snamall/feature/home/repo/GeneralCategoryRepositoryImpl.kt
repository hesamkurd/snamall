package ir.mamhesam.snamall.feature.home.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseGeneralCategory
import ir.mamhesam.snamall.feature.home.source.GeneralCategoryDataSource

class GeneralCategoryRepositoryImpl(val remoteGeneralCategoryDataSource: GeneralCategoryDataSource): GeneralCategoryRepository {
    override fun getGeneralCategory(): Single<List<ResponseGeneralCategory>> = remoteGeneralCategoryDataSource.getGeneralCategory()
}