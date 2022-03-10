package ir.mamhesam.snamall.feature.home.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponsePopularProduct

interface PopularProductDataSource {
    fun getPopularProduct(): Single<List<ResponsePopularProduct>>

}