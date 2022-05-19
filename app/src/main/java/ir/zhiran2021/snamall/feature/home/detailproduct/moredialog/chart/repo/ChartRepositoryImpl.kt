package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.chart.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseChart
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.chart.source.ChartDataSource

class ChartRepositoryImpl(val remoteChartDataSource: ChartDataSource):ChartRepository {
    override fun getChart(productId: Int): Single<List<ResponseChart>> = remoteChartDataSource.getChart(productId)
}