package ir.mamhesam.snamall.feature.search.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseSearch

interface SearchRepository {
    fun getSearch(search:String):Single<ResponseSearch>
}