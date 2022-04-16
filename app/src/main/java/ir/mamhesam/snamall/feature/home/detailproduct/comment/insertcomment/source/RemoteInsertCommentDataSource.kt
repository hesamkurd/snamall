package ir.mamhesam.snamall.feature.home.detailproduct.comment.insertcomment.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseInsertComment

class RemoteInsertCommentDataSource(val apiService: ApiService): InsertCommentDataSource {
    override fun insertComment(
        productId: Int,
        content: String,
        title: String,
        positive: String,
        negative: String,
        Advice: String
    ): Single<ResponseInsertComment> = apiService.insertComment(productId,content, title, positive, negative, Advice)
}