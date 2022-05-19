package ir.zhiran2021.snamall.feature.home.detailproduct.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponsCategory

class CatAdapter(val categories: List<ResponsCategory>):RecyclerView.Adapter<CatAdapter.GalleryViewHolder>() {

    lateinit var onClickCategory: OnClickCategory

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_categories,parent,false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val itemGallery = categories[position]
        holder.imgGallery.text = itemGallery.name

        holder.itemView.setOnClickListener {
            onClickCategory.onClickItem(itemGallery.name)
        }

    }

    fun setOnClickCategoryItem(onClickCategory: OnClickCategory){
        this.onClickCategory = onClickCategory
    }

    override fun getItemCount(): Int = categories.size

    class GalleryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgGallery = itemView.findViewById<TextView>(R.id.txt_category)
    }

    interface OnClickCategory{
        fun onClickItem(typeCat:String)
    }

}