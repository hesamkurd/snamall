package ir.mamhesam.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseRatingComment(

	@field:SerializedName("score_value")
	val scoreValue: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("score_name")
	val scoreName: String
)
