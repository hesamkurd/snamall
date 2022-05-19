package ir.zhiran2021.snamall.feature.home.allamazing.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseAllAmazing

interface AllAmazingDataSource {
    fun allAmazing(sort:Int):Single<List<ResponseAllAmazing>>
}