package ir.mamhesam.snamall.feature.home.detailproduct.property.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseTechnicalProperty

class RemoteTechnicalDataSource(val apiService: ApiService): TechnicalDataSource {
    override fun getTechnical(productId: Int): Single<List<ResponseTechnicalProperty>> = apiService.getTechnicalProperty(productId)
}