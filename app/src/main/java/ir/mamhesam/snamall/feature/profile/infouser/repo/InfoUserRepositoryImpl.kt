package ir.mamhesam.snamall.feature.profile.infouser.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseInfoUser
import ir.mamhesam.snamall.feature.profile.infouser.source.InfoUserDataSource

class InfoUserRepositoryImpl(val remoteInfoUserDataSource: InfoUserDataSource):InfoUserRepository {
    override fun getInfoUser(): Single<ResponseInfoUser> = remoteInfoUserDataSource.getInfoUser()
}