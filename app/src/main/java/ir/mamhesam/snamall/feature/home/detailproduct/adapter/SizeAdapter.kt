package ir.mamhesam.snamall.feature.home.detailproduct.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.data.ProductSizesItem

class SizeAdapter(val sizes: List<ProductSizesItem>): RecyclerView.Adapter<SizeAdapter.SizeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_size,parent,false)
        return SizeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        val sizeItem = sizes[position]
        holder.txt_size.text = sizeItem.sizesName
    }

    override fun getItemCount(): Int = sizes.size

    class SizeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txt_size = itemView.findViewById<TextView>(R.id.txt_size)

    }
}