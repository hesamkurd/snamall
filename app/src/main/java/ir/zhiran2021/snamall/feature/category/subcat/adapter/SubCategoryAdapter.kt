package ir.zhiran2021.snamall.feature.category.subcat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponseSubCat1
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.view.MyImageView

class SubCategoryAdapter(val categories: List<ResponseSubCat1>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<SubCategoryAdapter.GeneralCategoryViewHolder>() {

    lateinit var onClickCategory: OnClickCategory

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sub_category,parent,false)
        return GeneralCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GeneralCategoryViewHolder, position: Int) {
        val itemCategories = categories[position]
        imageLoadService.loadImage(holder.imgGeneralCategory, itemCategories.image)
        holder.txtTitleSubCatLevel.text = itemCategories.title

        holder.itemView.setOnClickListener {
            onClickCategory.onClickCatItem(itemCategories.id)
        }
    }

    fun setOnClickCat(onClickCategory: OnClickCategory){
        this.onClickCategory = onClickCategory
    }


    override fun getItemCount(): Int = categories.size

    class GeneralCategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgGeneralCategory = itemView.findViewById<MyImageView>(R.id.img_sub_cat_level)
        val txtTitleSubCatLevel = itemView.findViewById<TextView>(R.id.txt_tilte_subcat_level)
    }

    interface OnClickCategory{
        fun onClickCatItem(generalCatId:Int)
    }

}