package ir.mamhesam.snamall.feature.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.data.ResponseGeneralCategory
import ir.mamhesam.snamall.services.ImageLoadService
import ir.mamhesam.snamall.view.MyImageView

class GeneralCategoryAdapter(val categories: List<ResponseGeneralCategory>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<GeneralCategoryAdapter.GeneralCategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_general_category,parent,false)
        return GeneralCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GeneralCategoryViewHolder, position: Int) {
        val itemCategories = categories[position]
        imageLoadService.loadImage(holder.imgGeneralCategory, itemCategories.image)
        holder.txtGeneralCategory.text = itemCategories.title
    }

    override fun getItemCount(): Int = categories.size

    class GeneralCategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgGeneralCategory = itemView.findViewById<MyImageView>(R.id.img_general_category)
        val txtGeneralCategory = itemView.findViewById<TextView>(R.id.txt_general_category)
    }

}