package ir.zhiran2021.snamall.feature.profile.infouser.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseInfoUser

interface InfoUserRepository {
    fun getInfoUser():Single<ResponseInfoUser>
}