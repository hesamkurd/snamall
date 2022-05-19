package ir.zhiran2021.snamall.feature.home.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseBestSellProduct

interface BestSellDataSource {
    fun getBestSell():Single<List<ResponseBestSellProduct>>
}