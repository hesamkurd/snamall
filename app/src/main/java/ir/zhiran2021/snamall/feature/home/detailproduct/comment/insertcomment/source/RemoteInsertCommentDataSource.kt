package ir.zhiran2021.snamall.feature.home.detailproduct.comment.insertcomment.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseInsertComment
import ir.zhiran2021.snamall.data.ResponseShowScore

class RemoteInsertCommentDataSource(val apiService: ApiService): InsertCommentDataSource {
    override fun insertComment(
        productId: Int,
        content: String,
        title: String,
        positive: String,
        negative: String,
        Advice: String
    ): Single<ResponseInsertComment> = apiService.insertComment(productId,content, title, positive, negative, Advice)

    override fun insertCommentPro(
        productId: Int,
        content: String,
        title: String,
        positive: String,
        negative: String,
        Advice: String,
        score: String
    ): Single<ResponseInsertComment> = apiService.insertCommentPro(productId,content, title, positive, negative, Advice, score)

    override fun showScore(productId: Int): Single<ResponseShowScore> = apiService.showScore(productId)
}