package ir.zhiran2021.snamall.feature.home.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseAmazingMarket

interface AmazingMarketRepository {
    fun getAmazingMarket():Single<List<ResponseAmazingMarket>>
}