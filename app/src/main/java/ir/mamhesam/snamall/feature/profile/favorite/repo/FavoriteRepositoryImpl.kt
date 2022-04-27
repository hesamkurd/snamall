package ir.mamhesam.snamall.feature.profile.favorite.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseFavorite
import ir.mamhesam.snamall.feature.profile.favorite.source.FavoriteDataSource

class FavoriteRepositoryImpl(val remoteFavoriteDataSource: FavoriteDataSource):FavoriteRepository {
    override fun getFavorite(): Single<List<ResponseFavorite>> = remoteFavoriteDataSource.getFavorite()
}