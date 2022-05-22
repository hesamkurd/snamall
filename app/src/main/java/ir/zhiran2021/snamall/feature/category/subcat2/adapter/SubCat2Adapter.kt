package ir.zhiran2021.snamall.feature.category.subcat2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponseSubCat2
import ir.zhiran2021.snamall.feature.category.subcat.adapter.SubCatProductAdapter
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.utils.FreePercent
import ir.zhiran2021.snamall.utils.PriceConverter
import ir.zhiran2021.snamall.view.MyImageView

class SubCat2Adapter(val subCatItems:List<ResponseSubCat2>, val imageLoadService: ImageLoadService):
    RecyclerView.Adapter<SubCat2Adapter.SubCat2ViewHolder>() {

    lateinit var onClickSubCatProduct:OnClickSubCatProduct


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCat2ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_brand_product,parent,false)
        return SubCat2ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubCat2ViewHolder, position: Int) {
        val subCats = subCatItems[position]
        imageLoadService.loadImage(holder.imgGeneralCategory, subCats.image)
        holder.txtNameProduct.text = subCats.name
        if (subCats.offPercent.toString() != "0"){
            holder.txtPrice.paintFlags = android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
        }
        holder.lnrFree.visibility = if (subCats.offPercent.toString() != "0") View.VISIBLE else View.INVISIBLE
        holder.txtFree.visibility = if (subCats.offPercent.toString() != "0") View.VISIBLE else View.GONE
        holder.txtFreePrice.visibility = if (subCats.offPercent.toString() != "0") View.VISIBLE else View.GONE
        holder.txtFree.text = FreePercent.offPercent(subCats.offPercent.toString())
        holder.txtFreePrice.text = PriceConverter.priceConvert(subCats.offPrice.toString())
        holder.txtPrice.text = PriceConverter.priceConvert(subCats.price)

        holder.itemView.setOnClickListener {
            onClickSubCatProduct.onClickSubCatItem(subCats.id)
        }
    }

    fun setOnClickProductItem(onClickSubCatProduct: OnClickSubCatProduct){
        this.onClickSubCatProduct = onClickSubCatProduct
    }

    override fun getItemCount(): Int = subCatItems.size

    class SubCat2ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val imgGeneralCategory = itemView.findViewById<MyImageView>(R.id.img_brand_product)
        val txtNameProduct = itemView.findViewById<TextView>(R.id.txt_name_product)
        val txtFree = itemView.findViewById<TextView>(R.id.txt_free)
        val txtFreePrice = itemView.findViewById<TextView>(R.id.txt_free_price)
        val txtPrice = itemView.findViewById<TextView>(R.id.txt_price)
        val lnrFree = itemView.findViewById<LinearLayout>(R.id.lnr_free)
    }

    interface OnClickSubCatProduct{
        fun onClickSubCatItem(productId:Int)
    }
}