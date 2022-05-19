package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.chart.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseChart

class RemoteChartDataSource(val apiService: ApiService):ChartDataSource {
    override fun getChart(productId: Int): Single<List<ResponseChart>> = apiService.getChart(productId)
}