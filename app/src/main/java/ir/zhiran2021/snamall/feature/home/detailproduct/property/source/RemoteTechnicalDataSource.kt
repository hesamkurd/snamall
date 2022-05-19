package ir.zhiran2021.snamall.feature.home.detailproduct.property.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseTechnicalProperty

class RemoteTechnicalDataSource(val apiService: ApiService): TechnicalDataSource {
    override fun getTechnical(productId: Int): Single<List<ResponseTechnicalProperty>> = apiService.getTechnicalProperty(productId)
}