package ir.zhiran2021.snamall.feature.home.detailproduct.comment.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseRatingComment
import ir.zhiran2021.snamall.data.ResponseShowComment

class RemoteCommentDataSource(val apiService: ApiService): CommentDataSource {
    override fun showRatingComment(productId: Int): Single<List<ResponseRatingComment>> = apiService.showRatingComment(productId)
    override fun showComment(productId: Int): Single<List<ResponseShowComment>> = apiService.showComment(productId)
}