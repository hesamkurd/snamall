package ir.zhiran2021.snamall.feature.home.detailproduct.property.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseTechnicalProperty
import ir.zhiran2021.snamall.feature.home.detailproduct.property.source.TechnicalDataSource

class TechnicalRepositoryImpl(val remoteTechnicalDataSource: TechnicalDataSource): TechnicalRepository {
    override fun getTechnical(productId: Int): Single<List<ResponseTechnicalProperty>> = remoteTechnicalDataSource.getTechnical(productId)
}