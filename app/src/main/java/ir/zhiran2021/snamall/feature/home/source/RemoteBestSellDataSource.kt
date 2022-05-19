package ir.zhiran2021.snamall.feature.home.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseBestSellProduct

class RemoteBestSellDataSource(val apiService: ApiService):BestSellDataSource {
    override fun getBestSell(): Single<List<ResponseBestSellProduct>> = apiService.getBestSellProduct()
}