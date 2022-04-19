package ir.mamhesam.snamall.feature.home.subcatlevel1.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseSubCatLevel1

class RemoteSubCatLevel1DataSource(val apiService: ApiService):SubCatLevel1DataSource{
    override fun subCatLevel1(general_cat: Int): Single<List<ResponseSubCatLevel1>> = apiService.subCatLevel1(general_cat)
}