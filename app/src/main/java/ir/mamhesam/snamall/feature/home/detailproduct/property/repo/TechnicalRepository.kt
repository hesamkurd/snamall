package ir.mamhesam.snamall.feature.home.detailproduct.property.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseTechnicalProperty

interface TechnicalRepository {
    fun getTechnical(productId:Int): Single<List<ResponseTechnicalProperty>>
}