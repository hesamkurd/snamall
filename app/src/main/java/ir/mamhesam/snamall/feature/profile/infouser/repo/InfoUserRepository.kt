package ir.mamhesam.snamall.feature.profile.infouser.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseInfoUser

interface InfoUserRepository {
    fun getInfoUser():Single<ResponseInfoUser>
}