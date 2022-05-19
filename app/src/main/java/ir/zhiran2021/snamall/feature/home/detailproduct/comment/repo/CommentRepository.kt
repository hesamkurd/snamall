package ir.zhiran2021.snamall.feature.home.detailproduct.comment.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseRatingComment
import ir.zhiran2021.snamall.data.ResponseShowComment

interface CommentRepository {
    fun showRatingComment(productId: Int):Single<List<ResponseRatingComment>>
    fun showComment(productId: Int): Single<List<ResponseShowComment>>
}