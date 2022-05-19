package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.chart.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseChart

interface ChartDataSource {
    fun getChart(productId:Int):Single<List<ResponseChart>>
}