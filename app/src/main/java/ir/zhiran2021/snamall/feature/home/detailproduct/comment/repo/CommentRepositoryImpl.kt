package ir.zhiran2021.snamall.feature.home.detailproduct.comment.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseRatingComment
import ir.zhiran2021.snamall.data.ResponseShowComment
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.source.CommentDataSource

class CommentRepositoryImpl(val remoteCommentDataSource: CommentDataSource): CommentRepository {
    override fun showRatingComment(productId: Int): Single<List<ResponseRatingComment>> = remoteCommentDataSource.showRatingComment(productId)
    override fun showComment(productId: Int): Single<List<ResponseShowComment>> = remoteCommentDataSource.showComment(productId)
}