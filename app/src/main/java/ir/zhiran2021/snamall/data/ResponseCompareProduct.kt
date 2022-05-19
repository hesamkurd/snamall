package ir.zhiran2021.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseCompareProduct(

	@field:SerializedName("score2")
	val score2: Double,

	@field:SerializedName("id2")
	val id2: Int,

	@field:SerializedName("id1")
	val id1: Int,

	@field:SerializedName("property")
	val property: List<PropertyItemCompare>,

	@field:SerializedName("imageurl2")
	val imageurl2: String,

	@field:SerializedName("imageurl1")
	val imageurl1: String,

	@field:SerializedName("score1")
	val score1: Double,

	@field:SerializedName("name2")
	val name2: String,

	@field:SerializedName("name1")
	val name1: String,

	@field:SerializedName("price1")
	val price1: String,

	@field:SerializedName("price2")
	val price2: String
)

data class PropertyItemCompare(

	@field:SerializedName("main_category")
	val mainCategory: String,

	@field:SerializedName("value")
	val value: List<ValueItem>
)

data class ValueItem(

	@field:SerializedName("property2")
	val property2: String,

	@field:SerializedName("property1")
	val property1: String
)
