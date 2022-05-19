package ir.zhiran2021.snamall.feature.home.subcatlevel1.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseSubCatLevel1

class RemoteSubCatLevel1DataSource(val apiService: ApiService):SubCatLevel1DataSource{
    override fun subCatLevel1(general_cat: Int): Single<List<ResponseSubCatLevel1>> = apiService.subCatLevel1(general_cat)
}