package ir.zhiran2021.snamall.feature.home.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseGeneralCategory

interface GeneralCategoryRepository {
    fun getGeneralCategory(): Single<List<ResponseGeneralCategory>>
}