package ir.mamhesam.snamall.feature.category.subcat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.data.*
import ir.mamhesam.snamall.services.ImageLoadService
import ir.mamhesam.snamall.view.MyImageView

class PopularBrandAdapter(val brand: List<ResponsePopularBrand>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<PopularBrandAdapter.GeneralCategoryViewHolder>() {

    lateinit var onClickBrand: OnClickBrand

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_brand,parent,false)
        return GeneralCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GeneralCategoryViewHolder, position: Int) {
        val itemCategories = brand[position]
        imageLoadService.loadImage(holder.imgBrand, itemCategories.image)
        holder.txtTitleBrand.text = itemCategories.title

        holder.itemView.setOnClickListener {
            onClickBrand.onClickBrandItem(itemCategories.title)
        }


    }

    fun setOnClickBrandItem(onClickBrand: OnClickBrand){
        this.onClickBrand = onClickBrand
    }


    override fun getItemCount(): Int = brand.size

    class GeneralCategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgBrand = itemView.findViewById<MyImageView>(R.id.img_brand)
        val txtTitleBrand = itemView.findViewById<TextView>(R.id.txt_title_brand)
    }

    interface OnClickBrand{
        fun onClickBrandItem(brandName: String)
    }

}