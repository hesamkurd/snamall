package ir.mamhesam.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseTechnicalProperty(

	@field:SerializedName("property")
	val property: List<PropertyItem>,

	@field:SerializedName("main_category")
	val mainCategory: String
)

data class PropertyItem(

	@field:SerializedName("property")
	val property: String,

	@field:SerializedName("title")
	val title: String
)
