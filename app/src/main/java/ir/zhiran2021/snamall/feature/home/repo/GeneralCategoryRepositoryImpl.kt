package ir.zhiran2021.snamall.feature.home.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseGeneralCategory
import ir.zhiran2021.snamall.feature.home.source.GeneralCategoryDataSource

class GeneralCategoryRepositoryImpl(val remoteGeneralCategoryDataSource: GeneralCategoryDataSource): GeneralCategoryRepository {
    override fun getGeneralCategory(): Single<List<ResponseGeneralCategory>> = remoteGeneralCategoryDataSource.getGeneralCategory()
}