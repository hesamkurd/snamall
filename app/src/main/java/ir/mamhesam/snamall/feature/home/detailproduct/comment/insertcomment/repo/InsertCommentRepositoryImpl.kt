package ir.mamhesam.snamall.feature.home.detailproduct.comment.insertcomment.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseInsertComment
import ir.mamhesam.snamall.feature.home.detailproduct.comment.insertcomment.source.InsertCommentDataSource

class InsertCommentRepositoryImpl(val remoteInsertCommentDataSource: InsertCommentDataSource): InsertCommentRepository {
    override fun insertComment(
        productId: Int,
        content: String,
        title: String,
        positive: String,
        negative: String,
        Advice: String
    ): Single<ResponseInsertComment> = remoteInsertCommentDataSource.insertComment(productId, content, title, positive, negative, Advice)
}