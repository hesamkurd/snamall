package ir.mamhesam.snamall.feature.home.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseGeneralCategory

class RemoteGeneralCategoryDataSource(val apiService: ApiService): GeneralCategoryDataSource{
    override fun getGeneralCategory(): Single<List<ResponseGeneralCategory>> = apiService.getGeneralCategory()
}