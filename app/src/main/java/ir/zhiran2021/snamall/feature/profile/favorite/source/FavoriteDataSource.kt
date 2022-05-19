package ir.zhiran2021.snamall.feature.profile.favorite.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseFavorite

interface FavoriteDataSource {
    fun getFavorite():Single<List<ResponseFavorite>>
}