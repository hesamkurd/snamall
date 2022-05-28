package ir.zhiran2021.snamall.feature.home.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseAmazingMarket

interface AmazingMarketDataSource {
    fun getAmazingMarket():Single<List<ResponseAmazingMarket>>
}