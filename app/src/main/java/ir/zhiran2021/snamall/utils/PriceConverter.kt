package ir.zhiran2021.snamall.utils

import java.text.DecimalFormat

object PriceConverter {
    fun priceConvert(price: String):String{
        val decimalFormat= DecimalFormat("###,###")
        val prices=decimalFormat.format(Integer.valueOf(price))
        return "$prices تومان "
    }

    fun sellsCount(sell:String):String{
        return " $sell فروش رفته "
    }
}