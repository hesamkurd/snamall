package ir.zhiran2021.snamall.feature.profile.favorite.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseFavorite

class RemoteFavoriteDataSource(val apiService: ApiService):FavoriteDataSource {
    override fun getFavorite(): Single<List<ResponseFavorite>> = apiService.getFavorite()
}