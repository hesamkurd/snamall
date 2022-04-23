package ir.mamhesam.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseCheckOutList(

	@field:SerializedName("name_family")
	val nameFamily: String,

	@field:SerializedName("wallet")
	val wallet: String,

	@field:SerializedName("total_off_price")
	val totalOffPrice: Int,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("total_price")
	val totalPrice: Int,

	@field:SerializedName("shipping_cost")
	val shippingCost: Int,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("address_id")
	val addressId: Int,

	@field:SerializedName("delivery_time")
	val deliveryTime: String,

	@field:SerializedName("total_price_off")
	val totalPriceOff: Int,

	@field:SerializedName("product_item_deliveries")
	val productItemDeliveries: List<ProductItemDeliveriesItem>,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("postal_code")
	val postalCode: String,

	@field:SerializedName("payable_price")
	val payablePrice: Int
)

data class ProductItemDeliveriesItem(

	@field:SerializedName("iamge")
	val iamge: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("count")
	val count: String,

	@field:SerializedName("id")
	val id: String
)
