package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseCompareCat
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.source.CompareCatDataSource

class CompareCatRepositoryImpl(val remoteCompareCatDataSource: CompareCatDataSource):CompareCatRepository {
    override fun getCompareCat(productId: Int): Single<List<ResponseCompareCat>> = remoteCompareCatDataSource.getCompareCat(productId)
}