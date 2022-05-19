package ir.zhiran2021.snamall.feature.home.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseGeneralCategory

interface GeneralCategoryDataSource {
    fun getGeneralCategory(): Single<List<ResponseGeneralCategory>>

}