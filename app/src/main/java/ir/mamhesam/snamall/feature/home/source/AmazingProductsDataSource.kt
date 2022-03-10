package ir.mamhesam.snamall.feature.home.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseAmazingProducts

interface AmazingProductsDataSource {
    fun getAmazingProducts(): Single<List<ResponseAmazingProducts>>
}