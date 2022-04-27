package ir.mamhesam.snamall.feature.search.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseSearch

interface SearchDataSource {
    fun getSearch(search:String):Single<ResponseSearch>
}