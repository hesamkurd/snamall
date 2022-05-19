package ir.zhiran2021.snamall.feature.profile.infouser.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseInfoUser

interface InfoUserDataSource {
    fun getInfoUser():Single<ResponseInfoUser>
}