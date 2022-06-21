package ir.zhiran2021.snamall.feature.profile.auoth.rules.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseRules
import ir.zhiran2021.snamall.feature.profile.auoth.rules.source.RulesDataSource

class RulesRepositoryImpl(val remoteRulesDataSource: RulesDataSource):RulesRepository {
    override fun getRules(): Single<List<ResponseRules>> = remoteRulesDataSource.getRules()
}