package ir.zhiran2021.snamall.feature.category.generalcat.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseProductGeneralCat
import ir.zhiran2021.snamall.feature.category.generalcat.source.ProductGeneralCatDataSource

class ProductGeneralCatRepositoryImpl(val remoteProductGeneralCatDataSource: ProductGeneralCatDataSource):ProductGeneralCatRepository {
    override fun getProductGeneralCat(general_cat_id: Int): Single<List<ResponseProductGeneralCat>> = remoteProductGeneralCatDataSource.getProductGeneralCat(general_cat_id)
}