package ir.zhiran2021.snamall.feature.home.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseAmazingProducts

interface AmazingProductsDataSource {
    fun getAmazingProducts(): Single<List<ResponseAmazingProducts>>
}