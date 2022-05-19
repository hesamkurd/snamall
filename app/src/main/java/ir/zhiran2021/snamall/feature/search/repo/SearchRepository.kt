package ir.zhiran2021.snamall.feature.search.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseSearch

interface SearchRepository {
    fun getSearch(search:String):Single<ResponseSearch>
}