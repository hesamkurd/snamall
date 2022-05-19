package ir.zhiran2021.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("name_family")
	val nameFamily: String,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("token")
	val token: String
)
