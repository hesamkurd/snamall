package ir.zhiran2021.snamall.feature.category.subcat2.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseSubCat2
import ir.zhiran2021.snamall.feature.category.subcat2.source.SubCat2DataSource

class SubCat2RepositoryImpl(val remoteSubCat2DataSource: SubCat2DataSource):SubCat2Repository {
    override fun getSubCat2(subcat2_id: Int): Single<List<ResponseSubCat2>> = remoteSubCat2DataSource.getSubCat2(subcat2_id)
}