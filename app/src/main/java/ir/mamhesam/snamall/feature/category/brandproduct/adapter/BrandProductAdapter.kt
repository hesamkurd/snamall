package ir.mamhesam.snamall.feature.category.brandproduct.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.ui.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.data.ResponseBrandProduct
import ir.mamhesam.snamall.services.ImageLoadService
import ir.mamhesam.snamall.utils.FreePercent
import ir.mamhesam.snamall.utils.PriceConverter
import ir.mamhesam.snamall.view.MyImageView

class BrandProductAdapter(val product: List<ResponseBrandProduct>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<BrandProductAdapter.BrandProductViewHolder>() {

    lateinit var onClickCategory: OnClickCategory

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_brand_product,parent,false)
        return BrandProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: BrandProductViewHolder, position: Int) {
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
        holder.txtFreePrice.text = PriceConverter.priceConvert(itemCategories.offPrice.toString())
        holder.txtPrice.text = PriceConverter.priceConvert(itemCategories.price)



    }

    fun setOnClickCat(onClickCategory: OnClickCategory){
        this.onClickCategory = onClickCategory
    }


    override fun getItemCount(): Int = product.size

    class BrandProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
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