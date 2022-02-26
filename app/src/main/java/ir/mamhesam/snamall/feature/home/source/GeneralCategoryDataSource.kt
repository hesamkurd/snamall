package ir.mamhesam.snamall.feature.home.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseGeneralCategory

interface GeneralCategoryDataSource {
    fun getGeneralCategory(): Single<List<ResponseGeneralCategory>>

}