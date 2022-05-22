package ir.zhiran2021.snamall.feature.category.adapter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponseCategories
import ir.zhiran2021.snamall.data.SubcatItem
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.utils.PRODUCT_ID

class CategoryAdapter(val categories: List<ResponseCategories>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<CategoryAdapter.GeneralCategoryViewHolder>(), CategoryChildAdapter.OnClickCategory {

    val viewPool= RecyclerView.RecycledViewPool()
    var subcatItem = categories
    lateinit var view1:View



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralCategoryViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_parent,parent,false)
        view1 = view
        return GeneralCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GeneralCategoryViewHolder, position: Int) {
        val itemCategories = categories[position]

        holder.txtTitle.text = itemCategories.title
        val layoutManager = LinearLayoutManager(holder.rcChild.context,LinearLayoutManager.HORIZONTAL,false)
        layoutManager.initialPrefetchItemCount = itemCategories.subcat.size
        val categoryChildAdapter= CategoryChildAdapter(itemCategories.subcat,imageLoadService)
        categoryChildAdapter.setOnClickCat(this)


        holder.rcChild.layoutManager = layoutManager
        holder.rcChild.adapter = categoryChildAdapter


        holder.rcChild.setRecycledViewPool(viewPool)

    }



    override fun getItemCount(): Int = categories.size

    class GeneralCategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val txtTitle = itemView.findViewById<TextView>(R.id.txt_parent_category)
        val rcChild = itemView.findViewById<RecyclerView>(R.id.rc_category_child)
    }

    override fun onClickCatItem(generalCatId: Int) {

        val bundle = Bundle()
        bundle.putInt(PRODUCT_ID, generalCatId)
        Navigation.findNavController(view1).navigate(R.id.action_categoryFragment_to_subCat1Fragment,bundle)
    }


}