package ir.zhiran2021.snamall.feature.home.detailproduct.comment.insertcomment.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseInsertComment
import ir.zhiran2021.snamall.data.ResponseShowScore
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.insertcomment.source.InsertCommentDataSource

class InsertCommentRepositoryImpl(val remoteInsertCommentDataSource: InsertCommentDataSource) :
    InsertCommentRepository {
    override fun insertComment(
        productId: Int,
        content: String,
        title: String,
        positive: String,
        negative: String,
        Advice: String
    ): Single<ResponseInsertComment> = remoteInsertCommentDataSource.insertComment(
        productId,
        content,
        title,
        positive,
        negative,
        Advice
    )

    override fun insertCommentPro(
        productId: Int,
        content: String,
        title: String,
        positive: String,
        negative: String,
        Advice: String,
        score: String
    ): Single<ResponseInsertComment> = remoteInsertCommentDataSource.insertCommentPro(
        productId,
        content,
        title,
        positive,
        negative,
        Advice,
        score
    )

    override fun showScore(productId: Int): Single<ResponseShowScore> =
        remoteInsertCommentDataSource.showScore(productId)
}