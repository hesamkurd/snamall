package ir.mamhesam.snamall.feature.home.detailproduct.comment.insertcomment.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseInsertComment
import ir.mamhesam.snamall.data.ResponseShowScore

interface InsertCommentRepository {
    fun insertComment(
        productId: Int,
        content: String,
        title: String,
        positive: String,
        negative: String,
        Advice: String
    ): Single<ResponseInsertComment>

    fun insertCommentPro(
        productId: Int,
        content: String,
        title: String,
        positive: String,
        negative: String,
        Advice: String,
        score: String
    ): Single<ResponseInsertComment>

    fun showScore(productId: Int): Single<ResponseShowScore>

}