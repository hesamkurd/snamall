package ir.mamhesam.snamall.feature.home.detailproduct.comment.insertcomment.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseInsertComment

interface InsertCommentDataSource {
    fun insertComment(productId: Int,
                      content: String,
                      title: String,
                      positive: String,
                      negative: String,
                      Advice: String): Single<ResponseInsertComment>
}