package ir.mamhesam.snamall.feature.home.detailproduct.comment.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseRatingComment
import ir.mamhesam.snamall.data.ResponseShowComment

interface CommentDataSource {
    fun showRatingComment(productId: Int):Single<List<ResponseRatingComment>>
    fun showComment(productId: Int): Single<List<ResponseShowComment>>

}