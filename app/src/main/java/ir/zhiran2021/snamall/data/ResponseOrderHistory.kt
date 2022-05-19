package ir.zhiran2021.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseOrderHistory(

	@field:SerializedName("ref_id")
	val refId: String,

	@field:SerializedName("images")
	val imagesOrder: List<ImagesItemOrder>,

	@field:SerializedName("date_time")
	val dateTime: String,

	@field:SerializedName("total_price")
	val totalPrice: String,

	@field:SerializedName("id")
	val id: Int
)

data class ImagesItemOrder(

	@field:SerializedName("url")
	val url: String
)
