package ir.zhiran2021.snamall.feature.category.subcat.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponsePopularBrand
import ir.zhiran2021.snamall.data.ResponseSubCat1
import ir.zhiran2021.snamall.data.ResponseSubCatProduct
import ir.zhiran2021.snamall.feature.category.subcat.source.SubCatDataSource

class SubCatRepositoryImpl(val remoteSubCatDataSource: SubCatDataSource): SubCatRepository {
    override fun getSubCat(sub_id: Int): Single<List<ResponseSubCat1>> = remoteSubCatDataSource.getSubCat(sub_id)
    override fun popularBrand(sub_id: Int): Single<List<ResponsePopularBrand>> = remoteSubCatDataSource.popularBrand(sub_id)
    override fun subCatProduct(sub_id: Int): Single<List<ResponseSubCatProduct>> = remoteSubCatDataSource.subCatProduct(sub_id)
}