package ir.zhiran2021.snamall.feature.home.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponsePopularProduct

interface PopularProductDataSource {
    fun getPopularProduct(): Single<List<ResponsePopularProduct>>

}