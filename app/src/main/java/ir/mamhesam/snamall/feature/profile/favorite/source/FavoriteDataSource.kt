package ir.mamhesam.snamall.feature.profile.favorite.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseFavorite

interface FavoriteDataSource {
    fun getFavorite():Single<List<ResponseFavorite>>
}