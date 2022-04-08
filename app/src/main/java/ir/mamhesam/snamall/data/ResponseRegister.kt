package ir.mamhesam.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseRegister(

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("token")
	val token: String
)
