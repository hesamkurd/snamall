package ir.zhiran2021.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseSubCatLevel1(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("total_product")
	val totalProduct: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String
)
