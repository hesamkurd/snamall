package ir.mamhesam.snamall.feature.profile.favorite.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseFavorite

interface FavoriteRepository {
    fun getFavorite():Single<List<ResponseFavorite>>
}