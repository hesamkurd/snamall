package ir.mamhesam.snamall.feature.home.detailproduct.property.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseTechnicalProperty

interface TechnicalDataSource {
    fun getTechnical(productId:Int): Single<List<ResponseTechnicalProperty>>
}