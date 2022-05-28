package ir.zhiran2021.snamall.feature.home.allamazingmarket.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseAllAmazingMarket

interface AllAmazingMarketRepository {
    fun getAllAmazingMarket(sort:Int):Single<List<ResponseAllAmazingMarket>>
}