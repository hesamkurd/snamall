package ir.mamhesam.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseBrandBanner(

	@field:SerializedName("icon")
	val icon: String,

	@field:SerializedName("banner")
	val banner: String
)
