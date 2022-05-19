package ir.zhiran2021.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseCartList(

	@field:SerializedName("total_off_price")
	val totalOffPrice: Int,

	@field:SerializedName("total_price")
	val totalPrice: Int,

	@field:SerializedName("product_item")
	var productItem: List<ProductItemItem>,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("payable_price")
	val payablePrice: Int
)

data class ProductItemItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("off_price")
	val offPrice: Int,

	@field:SerializedName("color")
	val color: String,

	@field:SerializedName("size")
	val size: String,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("count")
	var count: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("off_percent")
	val offPercent: Int,

	@field:SerializedName("total_product_price")
	val totalProductPrice: Int
)
