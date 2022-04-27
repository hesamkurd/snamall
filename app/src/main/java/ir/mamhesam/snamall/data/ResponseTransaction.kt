package ir.mamhesam.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseTransaction(

	@field:SerializedName("ref_id")
	val refId: String,

	@field:SerializedName("message")
	val message: String
)
