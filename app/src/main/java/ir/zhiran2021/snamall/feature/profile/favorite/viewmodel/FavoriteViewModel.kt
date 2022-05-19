package ir.zhiran2021.snamall.feature.profile.favorite.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.EmptyState
import ir.zhiran2021.snamall.data.ResponseFavorite
import ir.zhiran2021.snamall.feature.profile.favorite.repo.FavoriteRepository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

class FavoriteViewModel(val favoriteRepository: FavoriteRepository):BaseViewModel() {

    val favoriteLiveData = MutableLiveData<List<ResponseFavorite>>()
    val emptyStateLiveData = MutableLiveData<EmptyState>()

    init {
      getFavorite()
    }

    fun getFavorite(){
        progressBarLiveData.value = true
        favoriteRepository.getFavorite().customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<List<ResponseFavorite>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseFavorite>) {
                    if (!t.isNullOrEmpty()){
                        favoriteLiveData.value = t

                    }else{
                        emptyStateLiveData.value = EmptyState(true, R.string.txt_empty_state_favorite)
                    }
                }

            })
    }
}