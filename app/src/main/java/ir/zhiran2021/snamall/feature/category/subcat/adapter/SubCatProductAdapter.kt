package ir.zhiran2021.snamall.feature.category.subcat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponseSubCatProduct
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.view.MyImageView

class SubCatProductAdapter(val product: List<ResponseSubCatProduct>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<SubCatProductAdapter.SimilarViewHolder>() {

    lateinit var onClickSubCatProduct: OnClickSubCatProduct

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sub_cat_product,parent,false)
        return SimilarViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimilarViewHolder, position: Int) {
        val similarItem = product[position]
        imageLoadService.loadImage(holder.imgProduct,similarItem.image)
        holder.txtName.text = similarItem.name

        holder.itemView.setOnClickListener {
            onClickSubCatProduct.onClickSubCatItem(similarItem.id.toInt())
        }
    }

    fun setOnClickProductItem(onClickSubCatProduct: OnClickSubCatProduct){
        this.onClickSubCatProduct = onClickSubCatProduct
    }

    override fun getItemCount(): Int = product.size

    class SimilarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgProduct = itemView.findViewById<MyImageView>(R.id.img_sub_cat_product)
        val txtName = itemView.findViewById<TextView>(R.id.txt_sub_cat_product)
    }

    interface OnClickSubCatProduct{
        fun onClickSubCatItem(productId:Int)
    }

}