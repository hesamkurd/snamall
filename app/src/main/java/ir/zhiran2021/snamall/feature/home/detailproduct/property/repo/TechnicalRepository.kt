package ir.zhiran2021.snamall.feature.home.detailproduct.property.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseTechnicalProperty

interface TechnicalRepository {
    fun getTechnical(productId:Int): Single<List<ResponseTechnicalProperty>>
}