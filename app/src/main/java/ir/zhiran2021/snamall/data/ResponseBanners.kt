package ir.zhiran2021.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseBanners(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("link")
	val link: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("type")
	val type: String
)
