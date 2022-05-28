package ir.zhiran2021.snamall.feature.category.subcat2.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponseSubCat2
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.utils.FreePercent
import ir.zhiran2021.snamall.utils.PriceConverter
import ir.zhiran2021.snamall.view.MyImageView

class SubCat2Adapter(val subCatItems:List<ResponseSubCat2>, val imageLoadService: ImageLoadService):
    RecyclerView.Adapter<SubCat2Adapter.SubCat2ViewHolder>() {

    lateinit var onClickSubCatProduct:OnClickSubCatProduct
    lateinit var context:Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCat2ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_subcat2_product,parent,false)
        return SubCat2ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubCat2ViewHolder, position: Int) {
        val subCats = subCatItems[position]
        imageLoadService.loadImage(holder.imgGeneralCategory, subCats.image)
        //imageLoadService.loadImage(holder.imgGeneralCategory, subCats.image)
        holder.txtNameProduct.text = subCats.name
        if (subCats.offPrice.toString() == "0"){
            holder.txtPrice.text = PriceConverter.priceConvert(subCats.price)
            holder.txtPrice.textSize = 12f
            holder.txtPrice.setTextColor(ContextCompat.getColor(context,R.color.grey_900))
            holder.lnrFree.visibility = View.INVISIBLE
            holder.txtFree.visibility = View.GONE
            holder.txtFreePrice.visibility = View.INVISIBLE

        }else{
            holder.txtPrice.text = PriceConverter.priceConvert(subCats.price)
            if (holder.txtFree.text != "0"){
                holder.txtPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

            }
            holder.txtFree.text = FreePercent.offPercent(subCats.offPercent.toString())
            holder.txtFreePrice.text = PriceConverter.priceConvert(subCats.offPrice.toString())
            holder.lnrFree.visibility = View.VISIBLE
            holder.txtFree.visibility = View.VISIBLE
            holder.txtFreePrice.visibility = View.VISIBLE
        }

        holder.itemView.setOnClickListener {
            onClickSubCatProduct.onClickSubCatItem(subCats.id)
        }
    }

    fun setOnClickProductItem(onClickSubCatProduct: OnClickSubCatProduct){
        this.onClickSubCatProduct = onClickSubCatProduct
    }

    override fun getItemCount(): Int = subCatItems.size

    class SubCat2ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val imgGeneralCategory = itemView.findViewById<MyImageView>(R.id.img_subcat2_product)
        val txtNameProduct = itemView.findViewById<TextView>(R.id.txt_name_product_subcat2)
        val txtFree = itemView.findViewById<TextView>(R.id.txt_free_subcat2)
        val txtFreePrice = itemView.findViewById<TextView>(R.id.txt_free_price_subcat2)
        val txtPrice = itemView.findViewById<TextView>(R.id.txt_price_subcat2)
        val lnrFree = itemView.findViewById<LinearLayout>(R.id.lnr_free_subcat2)
    }

    interface OnClickSubCatProduct{
        fun onClickSubCatItem(productId:Int)
    }
}