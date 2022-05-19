package ir.zhiran2021.snamall.feature.home.allamazing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponseAllAmazing
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.utils.FreePercent
import ir.zhiran2021.snamall.utils.PriceConverter
import ir.zhiran2021.snamall.view.MyImageView

class AllAmazingAdapter(val product: List<ResponseAllAmazing>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<AllAmazingAdapter.AllAmazingViewHolder>() {

    lateinit var onClickCategory: OnClickCategory

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllAmazingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_brand_product,parent,false)
        return AllAmazingViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllAmazingViewHolder, position: Int) {
        val itemCategories = product[position]
        imageLoadService.loadImage(holder.imgGeneralCategory, itemCategories.image)
        holder.txtNameProduct.text = itemCategories.name
        if (itemCategories.offPercent.toString() != "0"){
            holder.txtPrice.paintFlags = android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
        }
        holder.lnrFree.visibility = if (itemCategories.offPercent.toString() != "0") View.VISIBLE else View.GONE
        holder.txtFree.visibility = if (itemCategories.offPercent.toString() != "0") View.VISIBLE else View.GONE
        holder.txtFreePrice.visibility = if (itemCategories.offPercent.toString() != "0") View.VISIBLE else View.GONE
        holder.txtFree.text = FreePercent.offPercent(itemCategories.offPercent.toString())
        holder.txtFreePrice.text = PriceConverter.priceConvert(itemCategories.amazingPrice.toString())
        holder.txtPrice.text = PriceConverter.priceConvert(itemCategories.price.toString())



    }

    fun setOnClickCat(onClickCategory: OnClickCategory){
        this.onClickCategory = onClickCategory
    }


    override fun getItemCount(): Int = product.size

    class AllAmazingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgGeneralCategory = itemView.findViewById<MyImageView>(R.id.img_brand_product)
        val txtNameProduct = itemView.findViewById<TextView>(R.id.txt_name_product)
        val txtFree = itemView.findViewById<TextView>(R.id.txt_free)
        val txtFreePrice = itemView.findViewById<TextView>(R.id.txt_free_price)
        val txtPrice = itemView.findViewById<TextView>(R.id.txt_price)
        val lnrFree = itemView.findViewById<LinearLayout>(R.id.lnr_free)
    }

    interface OnClickCategory{
        fun onClickCatItem(generalCatId:Int)
    }

}