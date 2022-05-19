package ir.zhiran2021.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseCompareCat(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("off_price")
	val offPrice: String,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("off_percent")
	val offPercent: String
)
