package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.chart.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseChart

interface ChartRepository {
    fun getChart(productId:Int):Single<List<ResponseChart>>
}