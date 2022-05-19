package ir.zhiran2021.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseRegister(

	@field:SerializedName("code")
	var code: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("token")
	val token: String
)
