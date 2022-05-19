package ir.zhiran2021.snamall.feature.home.subcatlevel1.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseSubCatLevel1
import ir.zhiran2021.snamall.feature.home.subcatlevel1.source.SubCatLevel1DataSource

class SubCatLevel1RepositoryImpl(val remoteSubCatLevel1DataSource: SubCatLevel1DataSource):SubCatLevel1Repository {
    override fun subCatLevel1(general_cat: Int): Single<List<ResponseSubCatLevel1>> = remoteSubCatLevel1DataSource.subCatLevel1(general_cat)
}