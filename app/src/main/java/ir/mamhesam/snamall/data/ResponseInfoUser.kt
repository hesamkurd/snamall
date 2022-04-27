package ir.mamhesam.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseInfoUser(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("name_family")
	val nameFamily: String,

	@field:SerializedName("wallet")
	val wallet: String,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("proccesing")
	val proccesing: Int
)
