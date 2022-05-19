package ir.zhiran2021.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseShowAddress(

	@field:SerializedName("name_family")
	val nameFamily: String,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("postal_code")
	val postalCode: String
)
