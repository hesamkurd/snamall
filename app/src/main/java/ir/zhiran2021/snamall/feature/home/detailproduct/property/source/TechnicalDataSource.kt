package ir.zhiran2021.snamall.feature.home.detailproduct.property.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseTechnicalProperty

interface TechnicalDataSource {
    fun getTechnical(productId:Int): Single<List<ResponseTechnicalProperty>>
}