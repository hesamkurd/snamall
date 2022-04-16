package ir.mamhesam.snamall.feature.home.detailproduct.comment.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.base.customObserver
import ir.mamhesam.snamall.data.ResponseRatingComment
import ir.mamhesam.snamall.data.ResponseShowComment
import ir.mamhesam.snamall.feature.home.detailproduct.comment.repo.CommentRepository
import ir.mamhesam.snamall.utils.ShopSingleObserver

class CommentViewModel(val productId: Int, val commentRepository: CommentRepository): BaseViewModel() {

    val showRatingCommentLiveData= MutableLiveData<List<ResponseRatingComment>>()
    val showCommentLiveData = MutableLiveData<List<ResponseShowComment>>()
    val productIdLiveData = MutableLiveData<Int>()

    init {
        productIdLiveData.value = productId
        progressBarLiveData.value = true
        commentRepository.showRatingComment(productIdLiveData.value!!)
            .customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<List<ResponseRatingComment>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseRatingComment>) {
                    showRatingCommentLiveData.value = t
                }

            })

        commentRepository.showComment(productIdLiveData.value!!)
            .customObserver()
            .subscribe(object : ShopSingleObserver<List<ResponseShowComment>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseShowComment>) {
                    showCommentLiveData.value = t
                }

            })
    }
}