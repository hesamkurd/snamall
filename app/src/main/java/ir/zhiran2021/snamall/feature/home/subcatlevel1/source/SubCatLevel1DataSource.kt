package ir.zhiran2021.snamall.feature.home.subcatlevel1.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseSubCatLevel1

interface SubCatLevel1DataSource {
    fun subCatLevel1(general_cat:Int):Single<List<ResponseSubCatLevel1>>
}