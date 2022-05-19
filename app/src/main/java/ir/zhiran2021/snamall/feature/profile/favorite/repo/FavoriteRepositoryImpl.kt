package ir.zhiran2021.snamall.feature.profile.favorite.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseFavorite
import ir.zhiran2021.snamall.feature.profile.favorite.source.FavoriteDataSource

class FavoriteRepositoryImpl(val remoteFavoriteDataSource: FavoriteDataSource):FavoriteRepository {
    override fun getFavorite(): Single<List<ResponseFavorite>> = remoteFavoriteDataSource.getFavorite()
}