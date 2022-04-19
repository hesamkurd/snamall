package ir.mamhesam.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseSubCatProduct(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String
)
