package ir.zhiran2021.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseOrderDelivery(

	@field:SerializedName("ref_id")
	val refId: String,

	@field:SerializedName("images")
	val images: List<ImagesItemOrderDelivery>,

	@field:SerializedName("date_time")
	val dateTime: String,

	@field:SerializedName("total_price")
	val totalPrice: String,

	@field:SerializedName("id")
	val id: Int
)

data class ImagesItemOrderDelivery(

	@field:SerializedName("url")
	val url: String
)
