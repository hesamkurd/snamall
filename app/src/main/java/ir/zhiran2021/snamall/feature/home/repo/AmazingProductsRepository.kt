package ir.zhiran2021.snamall.feature.home.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseAmazingProducts

interface AmazingProductsRepository {
    fun getAmazingProducts(): Single<List<ResponseAmazingProducts>>
}