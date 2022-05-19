package ir.zhiran2021.snamall.feature.home.subcatlevel1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponseSubCatLevel1
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.view.MyImageView

class SubCatLevel1Adapter(val categories: List<ResponseSubCatLevel1>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<SubCatLevel1Adapter.GeneralCategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_subcat_level,parent,false)
        return GeneralCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GeneralCategoryViewHolder, position: Int) {
        val itemCategories = categories[position]
        imageLoadService.loadImage(holder.imgGeneralCategory, itemCategories.image)
        holder.txtTitleSubCatLevel.text = itemCategories.title
        holder.txtCountSubCatLevel.text = itemCategories.totalProduct


    }



    override fun getItemCount(): Int = categories.size

    class GeneralCategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgGeneralCategory = itemView.findViewById<MyImageView>(R.id.img_sub_cat_level)
        val txtTitleSubCatLevel = itemView.findViewById<TextView>(R.id.txt_tilte_subcat_level)
        val txtCountSubCatLevel = itemView.findViewById<TextView>(R.id.txt_count_subcat_level)
    }

    interface OnClickGeneralCategory{
        fun onClickGeneralItem(generalCatId:Int)
    }

}