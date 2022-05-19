package ir.zhiran2021.snamall.feature.home.detailproduct.comment.insertcomment.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.zhiran2021.snamall.base.BaseViewModel
import ir.zhiran2021.snamall.base.customObserver
import ir.zhiran2021.snamall.data.ResponseInsertComment
import ir.zhiran2021.snamall.data.ResponseShowScore
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.insertcomment.repo.InsertCommentRepository
import ir.zhiran2021.snamall.utils.ShopSingleObserver

class InsertCommentViewModel(
    val productId: Int,
    val insertCommentRepository: InsertCommentRepository
) : BaseViewModel() {

    val insertCommentLiveData = MutableLiveData<ResponseInsertComment>()
    val showScoreLiveData = MutableLiveData<ResponseShowScore>()

    init {
        progressBarLiveData.value = true
        insertCommentRepository.showScore(productId).customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : ShopSingleObserver<ResponseShowScore>(compositeDisposable) {
                override fun onSuccess(t: ResponseShowScore) {
                    showScoreLiveData.value = t
                }
            })
    }

    fun insertComment(
        productId: Int,
        content: String,
        title: String,
        positive: String,
        negative: String,
        Advice: String
    ) {
        insertCommentRepository.insertComment(productId, content, title, positive, negative, Advice)
            .customObserver()
            .subscribe(object : ShopSingleObserver<ResponseInsertComment>(compositeDisposable) {
                override fun onSuccess(t: ResponseInsertComment) {
                    insertCommentLiveData.value = t
                }

            })

    }

    fun insertCommentPro(
        productId: Int,
        content: String,
        title: String,
        positive: String,
        negative: String,
        Advice: String,
        score: String
    ) {
        insertCommentRepository.insertCommentPro(
            productId,
            content,
            title,
            positive,
            negative,
            Advice,
            score
        )
            .customObserver()
            .subscribe(object : ShopSingleObserver<ResponseInsertComment>(compositeDisposable) {
                override fun onSuccess(t: ResponseInsertComment) {
                    insertCommentLiveData.value = t
                }

            })

    }
}