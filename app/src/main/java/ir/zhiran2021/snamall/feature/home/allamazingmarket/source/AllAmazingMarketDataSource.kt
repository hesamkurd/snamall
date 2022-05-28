package ir.zhiran2021.snamall.feature.home.allamazingmarket.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseAllAmazingMarket

interface AllAmazingMarketDataSource {
    fun getAllAmazingMarket(sort:Int):Single<List<ResponseAllAmazingMarket>>
}