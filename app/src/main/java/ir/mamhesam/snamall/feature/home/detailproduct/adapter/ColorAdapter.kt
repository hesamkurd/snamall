package ir.mamhesam.snamall.feature.home.detailproduct.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.data.ProductColorsItem

class ColorAdapter(val colors: List<ProductColorsItem>): RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    lateinit var onClickColorItem: OnClickColorItem
    var selectedItemPos = -1
    var lastSelectedItemPos = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_colors,parent,false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val colorsItem = colors[position]
        if (position == selectedItemPos) holder.selectedBg() else holder.defaultBg()

        holder.txt_color.text = colorsItem.colorsName
        holder.cardView.setCardBackgroundColor(Color.parseColor(colorsItem.colorsCode))
        holder.itemView.setOnClickListener {
            selectedItemPos = holder.adapterPosition
            lastSelectedItemPos = if (lastSelectedItemPos == -1) selectedItemPos else{
                notifyItemChanged(lastSelectedItemPos)
                selectedItemPos
            }
            notifyItemChanged(selectedItemPos)
            onClickColorItem.onClickColorId(colorsItem.colorsId.toInt())
        }
    }

    fun setOnClickColor(onClickColorItem: OnClickColorItem){
        this.onClickColorItem = onClickColorItem
    }
    override fun getItemCount(): Int = colors.size

    class ColorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txt_color = itemView.findViewById<TextView>(R.id.txt_color)
        val cardView = itemView.findViewById<MaterialCardView>(R.id.mc)
        val rltv_color = itemView.findViewById<LinearLayout>(R.id.rltv_color)

        fun defaultBg(){
            rltv_color.background = ContextCompat.getDrawable(itemView.context,R.drawable.shape_rtlv_color)
        }
        fun selectedBg(){
            rltv_color.background = ContextCompat.getDrawable(itemView.context, R.drawable.shape_rtlv_color_selected)
        }
    }

    interface OnClickColorItem{
        fun onClickColorId(colorId: Int)
    }
}