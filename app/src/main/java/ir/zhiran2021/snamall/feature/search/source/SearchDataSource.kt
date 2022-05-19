package ir.zhiran2021.snamall.feature.search.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseSearch

interface SearchDataSource {
    fun getSearch(search:String):Single<ResponseSearch>
}