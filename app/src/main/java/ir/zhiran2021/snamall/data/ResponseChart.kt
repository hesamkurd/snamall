package ir.zhiran2021.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseChart(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("id")
	val id: String
)
