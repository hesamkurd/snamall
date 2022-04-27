package ir.mamhesam.snamall.feature.home.allamazing.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseAllAmazing

interface AllAmazingRepository {
    fun allAmazing(sort:Int):Single<List<ResponseAllAmazing>>
}