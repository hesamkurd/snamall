package ir.mamhesam.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseDetailsProduct(

    @field:SerializedName("comment_count")
	val commentCount: String,

    @field:SerializedName("subcat2_name")
	val subcat2Name: String,

    @field:SerializedName("subcat_2_id")
	val subcat2Id: String,

    @field:SerializedName("description")
	val description: String,

    @field:SerializedName("seller_image")
	val sellerImage: String,

    @field:SerializedName("similar_product")
	val similarProduct: List<SimilarProductItem>,

    @field:SerializedName("score")
	val score: Float,

    @field:SerializedName("number")
	val number: String,

    @field:SerializedName("garanty_description")
	val garantyDescription: String,

    @field:SerializedName("seller_name")
	val sellerName: String,

    @field:SerializedName("price")
	val price: String,

    @field:SerializedName("cat_id")
	val catId: String,

    @field:SerializedName("product_sizes")
	val productSizes: List<ProductSizesItem>,

    @field:SerializedName("id")
	val id: Int,

    @field:SerializedName("seller_id")
	val sellerId: Int,

    @field:SerializedName("sub_brand_id")
	val subBrandId: String,

    @field:SerializedName("off_price")
	val offPrice: String,

    @field:SerializedName("images")
	val imagesOrder: List<ImagesItem>,

    @field:SerializedName("comments")
	val comments: List<CommentsItem>,

    @field:SerializedName("cat_name")
	val catName: String,

    @field:SerializedName("brand_name")
	val brandName: String,

    @field:SerializedName("sub_brand_name")
	val subBrandName: String,

    @field:SerializedName("brand_id")
	val brandId: String,

    @field:SerializedName("name")
	val name: String,

    @field:SerializedName("subcat1_name")
	val subcat1Name: String,

    @field:SerializedName("product_colors")
	val productColors: List<ProductColorsItem>,

    @field:SerializedName("off_percent")
	val offPercent: String,

    @field:SerializedName("subcat1_id")
	val subcat1Id: String,

    @field:SerializedName("properties")
	val sproperties: List<PropertiesItem>,

    @field:SerializedName("status")
	val status: String
)

data class CommentsItem(

	@field:SerializedName("name_family")
	val nameFamily: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("content")
	val content: String,

	@field:SerializedName("comment_date")
	val commentData: String
)

data class ImagesItem(

	@field:SerializedName("image")
	val image: String
)

data class PropertiesItem(

	@field:SerializedName("value")
	val value: String,

	@field:SerializedName("property_name")
	val propertyName: String
)

data class ProductSizesItem(

	@field:SerializedName("sizes_id")
	val sizesId: String,

	@field:SerializedName("sizes_name")
	val sizesName: String
)

data class SimilarProductItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("url")
	val url: String
)

data class ProductColorsItem(

	@field:SerializedName("colors_name")
	val colorsName: String,

	@field:SerializedName("colors_id")
	val colorsId: String,

	@field:SerializedName("colors_code")
	val colorsCode: String
)
