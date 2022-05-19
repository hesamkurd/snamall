package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ValueItem

class CompareChildAdapter(private val compare: List<ValueItem>): RecyclerView.Adapter<CompareChildAdapter.CompareCategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompareCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_compare_chile,parent,false)
        return CompareCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompareCategoryViewHolder, position: Int) {
        val itemCompares = compare[position]
        if (itemCompares.property1.isEmpty()){
            holder.txtPrOne.text = ""
        }else{
            holder.txtPrOne.text = itemCompares.property1

        }

        if (itemCompares.property2.isEmpty()){
            holder.txtPrTwo.text = ""
        }else{
            holder.txtPrTwo.text = itemCompares.property2

        }


    }

    override fun getItemCount(): Int = compare.size

    class CompareCategoryViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        val txtPrOne = itemView.findViewById<TextView>(R.id.txt_compareItem_one)
        val txtPrTwo = itemView.findViewById<TextView>(R.id.txt_compareItem_two)
    }

}