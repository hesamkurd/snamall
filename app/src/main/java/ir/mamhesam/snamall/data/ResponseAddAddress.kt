package ir.mamhesam.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseAddAddress(

	@field:SerializedName("number")
	val number: String,

	@field:SerializedName("unit")
	val unit: String,

	@field:SerializedName("name_family")
	val nameFamily: String,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("state")
	val state: String,

	@field:SerializedName("postal_code")
	val postalCode: String,

	@field:SerializedName("lang")
	val lang: String,

	@field:SerializedName("lat")
	val lat: String
)
