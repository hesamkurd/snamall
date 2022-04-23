package ir.mamhesam.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseChangeCountItem(

	@field:SerializedName("count")
	val count: String,

	@field:SerializedName("message")
	val message: String
)
