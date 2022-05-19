package ir.zhiran2021.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseSearch(

	@field:SerializedName("part1")
	val part1: List<Part1Item>,

	@field:SerializedName("part2")
	val part2: List<Part2Item>
)

data class Part2Item(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String
)

data class Part1Item(

	@field:SerializedName("cat_id")
	val catId: String,

	@field:SerializedName("title")
	val title: String
)
