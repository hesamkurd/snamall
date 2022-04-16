package ir.mamhesam.snamall.feature.home.detailproduct.comment.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseRatingComment
import ir.mamhesam.snamall.data.ResponseShowComment

class RemoteCommentDataSource(val apiService: ApiService): CommentDataSource {
    override fun showRatingComment(productId: Int): Single<List<ResponseRatingComment>> = apiService.showRatingComment(productId)
    override fun showComment(productId: Int): Single<List<ResponseShowComment>> = apiService.showComment(productId)
}