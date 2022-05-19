package ir.zhiran2021.snamall.feature.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponseGeneralCategory
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.view.MyImageView

class GeneralCategoryAdapter(val categories: List<ResponseGeneralCategory>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<GeneralCategoryAdapter.GeneralCategoryViewHolder>() {

    lateinit var onClickGeneralCategory: OnClickGeneralCategory

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_general_category,parent,false)
        return GeneralCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GeneralCategoryViewHolder, position: Int) {
        val itemCategories = categories[position]
        imageLoadService.loadImage(holder.imgGeneralCategory, itemCategories.image)
        holder.txtGeneralCategory.text = itemCategories.title

        holder.itemView.setOnClickListener {
            onClickGeneralCategory.onClickGeneralItem(itemCategories.id,itemCategories.title)
        }
    }

    fun setOnClickGeneral(onClickGeneralCategory: OnClickGeneralCategory){
        this.onClickGeneralCategory = onClickGeneralCategory
    }

    override fun getItemCount(): Int = categories.size

    class GeneralCategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgGeneralCategory = itemView.findViewById<MyImageView>(R.id.img_general_category)
        val txtGeneralCategory = itemView.findViewById<TextView>(R.id.txt_general_category)
    }

    interface OnClickGeneralCategory{
        fun onClickGeneralItem(generalCatId:Int,generalTitle:String)
    }

}