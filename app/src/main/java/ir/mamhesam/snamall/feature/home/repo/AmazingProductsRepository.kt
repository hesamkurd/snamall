package ir.mamhesam.snamall.feature.home.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseAmazingProducts

interface AmazingProductsRepository {
    fun getAmazingProducts(): Single<List<ResponseAmazingProducts>>
}