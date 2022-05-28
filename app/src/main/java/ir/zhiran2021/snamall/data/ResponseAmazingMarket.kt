package ir.zhiran2021.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseAmazingMarket(

	@field:SerializedName("number")
	val number: Int,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("amazing_time")
	val amazingTime: Long,

	@field:SerializedName("sells_count")
	val sellsCount: Int,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("amazing_price")
	val amazingPrice: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("off_percent")
	val offPercent: Int
)
