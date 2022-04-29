package ir.mamhesam.snamall.feature.home.detailproduct.comment.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseRatingComment
import ir.mamhesam.snamall.data.ResponseShowComment
import ir.mamhesam.snamall.data.ResponseShowScore

interface CommentRepository {
    fun showRatingComment(productId: Int):Single<List<ResponseRatingComment>>
    fun showComment(productId: Int): Single<List<ResponseShowComment>>
}