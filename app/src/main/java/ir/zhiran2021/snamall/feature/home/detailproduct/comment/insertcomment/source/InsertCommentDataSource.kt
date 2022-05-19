package ir.zhiran2021.snamall.feature.home.detailproduct.comment.insertcomment.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseInsertComment
import ir.zhiran2021.snamall.data.ResponseShowScore

interface InsertCommentDataSource {
    fun insertComment(productId: Int,
                      content: String,
                      title: String,
                      positive: String,
                      negative: String,
                      Advice: String): Single<ResponseInsertComment>

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