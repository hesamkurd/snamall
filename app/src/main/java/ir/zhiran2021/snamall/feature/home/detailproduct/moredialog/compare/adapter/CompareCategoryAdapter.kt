package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponseCompareCat
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.view.MyImageView

class CompareCategoryAdapter(
    private val compare: List<ResponseCompareCat>,
    private val imageLoadingServices: ImageLoadService): RecyclerView.Adapter<CompareCategoryAdapter.CompareCategoryViewHolder>() {

    lateinit var onClickCategoryItem: OnClickCategoryItem

    fun setonClickCategoryItem(onClickCategoryItem: OnClickCategoryItem){

        this.onClickCategoryItem = onClickCategoryItem

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompareCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_compare,parent,false)
        return CompareCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompareCategoryViewHolder, position: Int) {
        val itemCompares = compare[position]
        imageLoadingServices.loadImage(holder.imgCompare , itemCompares.image)
        holder.txtCompare.text = itemCompares.name

        holder.itemView.setOnClickListener {

           onClickCategoryItem.onClickCategory(itemCompares.id)
        }

    }

    override fun getItemCount(): Int = compare.size

    class CompareCategoryViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        val imgCompare = itemView.findViewById<MyImageView>(R.id.img_category_compare)
        val txtCompare = itemView.findViewById<TextView>(R.id.txt_compare_category)
    }

    interface OnClickCategoryItem{
        fun onClickCategory(productIdTwo: Int)
    }
}