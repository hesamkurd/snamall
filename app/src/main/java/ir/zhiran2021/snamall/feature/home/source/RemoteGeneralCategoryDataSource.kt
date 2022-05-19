package ir.zhiran2021.snamall.feature.home.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseGeneralCategory

class RemoteGeneralCategoryDataSource(val apiService: ApiService): GeneralCategoryDataSource{
    override fun getGeneralCategory(): Single<List<ResponseGeneralCategory>> = apiService.getGeneralCategory()
}