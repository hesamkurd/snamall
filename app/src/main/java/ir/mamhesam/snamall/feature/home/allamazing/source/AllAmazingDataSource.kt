package ir.mamhesam.snamall.feature.home.allamazing.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseAllAmazing

interface AllAmazingDataSource {
    fun allAmazing(sort:Int):Single<List<ResponseAllAmazing>>
}