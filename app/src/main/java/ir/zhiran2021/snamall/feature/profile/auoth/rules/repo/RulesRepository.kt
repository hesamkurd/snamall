package ir.zhiran2021.snamall.feature.profile.auoth.rules.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseRules

interface RulesRepository {
    fun getRules():Single<List<ResponseRules>>
}