package ir.mamhesam.snamall.feature.profile.favorite.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseFavorite

class RemoteFavoriteDataSource(val apiService: ApiService):FavoriteDataSource {
    override fun getFavorite(): Single<List<ResponseFavorite>> = apiService.getFavorite()
}