package ir.zhiran2021.snamall.feature.profile.auoth.privacy.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponsePrivacy

interface PrivacyRepository {
    fun getPrivacy():Single<List<ResponsePrivacy>>
}