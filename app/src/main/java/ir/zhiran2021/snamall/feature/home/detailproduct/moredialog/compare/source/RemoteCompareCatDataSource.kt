package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseCompareCat

class RemoteCompareCatDataSource(val apiService: ApiService):CompareCatDataSource {
    override fun getCompareCat(productId: Int): Single<List<ResponseCompareCat>> = apiService.getCompareCat(productId)
}