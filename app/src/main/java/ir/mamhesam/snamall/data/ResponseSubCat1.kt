package ir.mamhesam.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseSubCat1(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String
)
