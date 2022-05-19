package ir.zhiran2021.snamall.feature.home.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseBestSellProduct

interface BestSellRepository {
    fun getBestSell():Single<List<ResponseBestSellProduct>>
}