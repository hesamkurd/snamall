package ir.zhiran2021.snamall.feature.home.detailproduct.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ProductSizesItem

class SizeAdapter(val sizes: List<ProductSizesItem>): RecyclerView.Adapter<SizeAdapter.SizeViewHolder>() {

    lateinit var onClickSizeItem: OnClickSizeItem
    var selectedItemPos = -1
    var selectedLastItemPos = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_size,parent,false)
        return SizeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        val sizeItem = sizes[position]
        holder.txt_size.text = sizeItem.sizesName
        if (position == selectedItemPos) holder.selectedBg() else holder.defaultBg()

        holder.itemView.setOnClickListener {
            selectedItemPos = holder.adapterPosition
            selectedLastItemPos = if (selectedLastItemPos == -1) selectedItemPos else{
                notifyItemChanged(selectedLastItemPos)
                selectedItemPos
            }
            notifyItemChanged(selectedItemPos)
            onClickSizeItem.onClickSizeId(sizeItem.sizesId.toInt())
        }
    }

    fun setOnClickSize(onClickSizeItem: OnClickSizeItem){
        this.onClickSizeItem = onClickSizeItem
    }
    override fun getItemCount(): Int = sizes.size

    class SizeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txt_size = itemView.findViewById<TextView>(R.id.txt_size)
        fun defaultBg(){
            txt_size.background = ContextCompat.getDrawable(itemView.context,R.drawable.shape_text_size)
        }
        fun selectedBg(){
            txt_size.background = ContextCompat.getDrawable(itemView.context, R.drawable.shape_text_size_selected)
            txt_size.setTextColor(ContextCompat.getColor(itemView.context,R.color.white))
        }
    }

    interface OnClickSizeItem{
        fun onClickSizeId(sizeId: Int)
    }

}