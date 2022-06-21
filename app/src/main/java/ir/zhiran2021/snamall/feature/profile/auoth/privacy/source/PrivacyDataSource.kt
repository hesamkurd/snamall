package ir.zhiran2021.snamall.feature.profile.auoth.privacy.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponsePrivacy

interface PrivacyDataSource {
    fun getPrivacy():Single<List<ResponsePrivacy>>
}