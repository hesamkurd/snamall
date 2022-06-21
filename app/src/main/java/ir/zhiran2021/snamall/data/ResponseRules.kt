package ir.zhiran2021.snamall.data

import com.google.gson.annotations.SerializedName

data class ResponseRules(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("text")
	val text: String
)
