package ir.zhiran2021.snamall.feature.category.subcat1.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponseProductSubCat1
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.utils.FreePercent
import ir.zhiran2021.snamall.utils.PriceConverter
import ir.zhiran2021.snamall.view.MyImageView

class ProductSubCat1Adapter(val products:List<ResponseProductSubCat1>,val imageLoadService: ImageLoadService):RecyclerView.Adapter<ProductSubCat1Adapter.ProductSubCat1ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSubCat1ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_subcat1_product,parent,false)
        return ProductSubCat1ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductSubCat1ViewHolder, position: Int) {
        val productItem = products[position]
        imageLoadService.loadImage(holder.imgGeneralCategory,productItem.image)
        holder.txtNameProduct.text = productItem.name
        if (productItem.offPercent != "0"){
            holder.txtPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
        holder.lnrFree.visibility = if (productItem.offPercent != "0") View.VISIBLE else View.INVISIBLE
        holder.txtFree.visibility = if (productItem.offPercent != "0") View.VISIBLE else View.GONE
        holder.txtFreePrice.visibility = if (productItem.offPercent != "0") View.VISIBLE else View.GONE
        holder.txtPrice.text = PriceConverter.priceConvert(productItem.price)
        holder.txtFree.text = FreePercent.offPercent(productItem.offPercent)
        holder.txtFreePrice.text = PriceConverter.priceConvert(productItem.offPrice.toString())
    }

    override fun getItemCount(): Int = products.size

    class ProductSubCat1ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val imgGeneralCategory = itemView.findViewById<MyImageView>(R.id.img_subcat1_product)
        val txtNameProduct = itemView.findViewById<TextView>(R.id.txt_name_product_subcat1)
        val txtFree = itemView.findViewById<TextView>(R.id.txt_free_subcat1)
        val txtFreePrice = itemView.findViewById<TextView>(R.id.txt_free_price_subcat1)
        val txtPrice = itemView.findViewById<TextView>(R.id.txt_price_subcat1)
        val lnrFree = itemView.findViewById<LinearLayout>(R.id.lnr_free_subcat1)
    }
}