package ir.mamhesam.snamall.feature.home.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponsePopularProduct

interface PopularProductRepository {
    fun getPopularProduct(): Single<List<ResponsePopularProduct>>
}