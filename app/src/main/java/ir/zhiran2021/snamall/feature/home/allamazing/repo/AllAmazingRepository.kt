package ir.zhiran2021.snamall.feature.home.allamazing.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseAllAmazing

interface AllAmazingRepository {
    fun allAmazing(sort:Int):Single<List<ResponseAllAmazing>>
}