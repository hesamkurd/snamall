package ir.zhiran2021.snamall.feature.profile.auoth.rules.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseRules

class RemoteRulesDataSource(val apiService: ApiService):RulesDataSource {
    override fun getRules(): Single<List<ResponseRules>> = apiService.getRules()
}