package ir.zhiran2021.snamall.feature.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.PropertyItem
import ir.zhiran2021.snamall.data.SubcatItem
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.view.MyImageView

class CategoryChildAdapter(val properties: List<SubcatItem>, val imageLoadService: ImageLoadService): RecyclerView.Adapter<CategoryChildAdapter.PropertyViewHolder>() {
    lateinit var onClickCategory: OnClickCategory

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {

        val propertyItem = properties[position]
        imageLoadService.loadImage(holder.imgCategory,propertyItem.subImage)
        holder.txtValue.text = propertyItem.subTitle

        holder.itemView.setOnClickListener {
            onClickCategory.onClickCatItem(propertyItem.subId.toInt())
        }

    }
    fun setOnClickCat(onClickCategory: OnClickCategory){
        this.onClickCategory = onClickCategory
    }

    override fun getItemCount(): Int = properties.size

    class PropertyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgCategory = itemView.findViewById<MyImageView>(R.id.img_sub_cat_level)
        val txtValue = itemView.findViewById<TextView>(R.id.txt_tilte_subcat_level)

    }
    interface OnClickCategory{
        fun onClickCatItem(generalCatId: Int)
    }

}