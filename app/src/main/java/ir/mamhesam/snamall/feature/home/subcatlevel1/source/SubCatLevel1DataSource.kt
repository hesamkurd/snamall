package ir.mamhesam.snamall.feature.home.subcatlevel1.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseSubCatLevel1

interface SubCatLevel1DataSource {
    fun subCatLevel1(general_cat:Int):Single<List<ResponseSubCatLevel1>>
}