package ir.mamhesam.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseOrderDetail(

	@field:SerializedName("ref_id")
	val refId: String,

	@field:SerializedName("shipping_price")
	val shippingPrice: String,

	@field:SerializedName("name_family")
	val nameFamily: String,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("date_time")
	val dateTime: String,

	@field:SerializedName("total_price")
	val totalPrice: String,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("shipping_code")
	val shippingCode: String,

	@field:SerializedName("pay_type")
	val payType: String,

	@field:SerializedName("order_detail")
	val orderDetail: List<OrderDetailItem>,

	@field:SerializedName("postal_code")
	val postalCode: String,

	@field:SerializedName("status")
	val status: String
)

data class OrderDetailItem(

	@field:SerializedName("size_name")
	val sizeName: String,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("count")
	val count: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("color_name")
	val colorName: String,

	@field:SerializedName("color_code")
	val colorCode: String
)
