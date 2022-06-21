package ir.zhiran2021.snamall.feature.profile.auoth.privacy.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponsePrivacy
import ir.zhiran2021.snamall.feature.profile.auoth.privacy.source.PrivacyDataSource

class PrivacyRepositoryImpl(val remotePrivacyDataSource: PrivacyDataSource):PrivacyRepository {
    override fun getPrivacy(): Single<List<ResponsePrivacy>> = remotePrivacyDataSource.getPrivacy()
}