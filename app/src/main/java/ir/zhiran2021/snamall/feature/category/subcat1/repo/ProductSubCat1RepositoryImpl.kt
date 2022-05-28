package ir.zhiran2021.snamall.feature.category.subcat1.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseProductSubCat1
import ir.zhiran2021.snamall.feature.category.subcat1.source.ProductSubCat1DataSource

class ProductSubCat1RepositoryImpl(val remoteProductSubCat1DataSource: ProductSubCat1DataSource):ProductSubCat1Repository {
    override fun getProductSubCat1(subcat1_id: Int): Single<List<ResponseProductSubCat1>> = remoteProductSubCat1DataSource.getProductSubCat1(subcat1_id)
}