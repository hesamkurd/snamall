package ir.zhiran2021.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseBestSellProduct(

	@field:SerializedName("number")
	val number: String,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)
