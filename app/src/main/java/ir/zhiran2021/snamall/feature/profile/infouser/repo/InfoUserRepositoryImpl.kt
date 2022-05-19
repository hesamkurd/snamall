package ir.zhiran2021.snamall.feature.profile.infouser.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseInfoUser
import ir.zhiran2021.snamall.feature.profile.infouser.source.InfoUserDataSource

class InfoUserRepositoryImpl(val remoteInfoUserDataSource: InfoUserDataSource):InfoUserRepository {
    override fun getInfoUser(): Single<ResponseInfoUser> = remoteInfoUserDataSource.getInfoUser()
}