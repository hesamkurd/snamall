package ir.mamhesam.snamall.feature.home.detailproduct.comment.insertcomment.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.mamhesam.snamall.base.BaseViewModel
import ir.mamhesam.snamall.base.customObserver
import ir.mamhesam.snamall.data.ResponseInsertComment
import ir.mamhesam.snamall.feature.home.detailproduct.comment.insertcomment.repo.InsertCommentRepository
import ir.mamhesam.snamall.utils.ShopSingleObserver

class InsertCommentViewModel(val productId: Int,val insertCommentRepository: InsertCommentRepository): BaseViewModel() {

    val insertCommentLiveData = MutableLiveData<ResponseInsertComment>()

    fun insertComment(productId: Int,
                      content: String,
                      title: String,
                      positive: String,
                      negative: String,
                      Advice: String){
        insertCommentRepository.insertComment(productId, content, title, positive, negative, Advice)
            .customObserver().subscribe(object : ShopSingleObserver<ResponseInsertComment>(compositeDisposable){
                override fun onSuccess(t: ResponseInsertComment) {
                    insertCommentLiveData.value = t
                }

            })

    }
}