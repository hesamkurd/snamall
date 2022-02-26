package ir.mamhesam.snamall.feature.home.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseGeneralCategory

interface GeneralCategoryRepository {
    fun getGeneralCategory(): Single<List<ResponseGeneralCategory>>
}