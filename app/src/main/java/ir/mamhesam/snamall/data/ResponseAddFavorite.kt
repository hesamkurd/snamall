package ir.mamhesam.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseAddFavorite(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)
