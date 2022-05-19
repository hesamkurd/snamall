package ir.zhiran2021.snamall.feature.home.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponsePopularProduct

interface PopularProductRepository {
    fun getPopularProduct(): Single<List<ResponsePopularProduct>>
}