package ir.zhiran2021.snamall.feature.profile.favorite.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseFavorite

interface FavoriteRepository {
    fun getFavorite():Single<List<ResponseFavorite>>
}