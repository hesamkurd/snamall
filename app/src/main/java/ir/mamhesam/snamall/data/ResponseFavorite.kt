package ir.mamhesam.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseFavorite(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("url")
	val url: String
)
