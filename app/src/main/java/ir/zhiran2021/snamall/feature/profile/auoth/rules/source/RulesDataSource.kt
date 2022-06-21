package ir.zhiran2021.snamall.feature.profile.auoth.rules.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseRules

interface RulesDataSource {
    fun getRules():Single<List<ResponseRules>>
}