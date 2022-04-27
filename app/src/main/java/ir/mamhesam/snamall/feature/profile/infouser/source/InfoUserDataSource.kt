package ir.mamhesam.snamall.feature.profile.infouser.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseInfoUser

interface InfoUserDataSource {
    fun getInfoUser():Single<ResponseInfoUser>
}