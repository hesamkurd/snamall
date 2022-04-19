package ir.mamhesam.snamall.feature.category.subcat.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponsePopularBrand
import ir.mamhesam.snamall.data.ResponseSubCat1
import ir.mamhesam.snamall.data.ResponseSubCatProduct
import ir.mamhesam.snamall.feature.category.subcat.source.RemoteSubCatDataSource
import ir.mamhesam.snamall.feature.category.subcat.source.SubCatDataSource

class SubCatRepositoryImpl(val remoteSubCatDataSource: SubCatDataSource): SubCatRepository {
    override fun getSubCat(sub_id: Int): Single<List<ResponseSubCat1>> = remoteSubCatDataSource.getSubCat(sub_id)
    override fun popularBrand(sub_id: Int): Single<List<ResponsePopularBrand>> = remoteSubCatDataSource.popularBrand(sub_id)
    override fun subCatProduct(sub_id: Int): Single<List<ResponseSubCatProduct>> = remoteSubCatDataSource.subCatProduct(sub_id)
}