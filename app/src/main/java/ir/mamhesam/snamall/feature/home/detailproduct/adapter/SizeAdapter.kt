package ir.mamhesam.snamall.feature.home.detailproduct.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.data.ProductColorsItem

class ColorAdapter(val colors: List<ProductColorsItem>): RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_color_product,parent,false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val colorsItem = colors[position]
        holder.txt_color.text = colorsItem.colorsName
        holder.cardView.setBackgroundColor(Color.parseColor(colorsItem.colorsCode))
    }

    override fun getItemCount(): Int = colors.size

    class ColorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txt_color = itemView.findViewById<TextView>(R.id.txt_color)
        val cardView = itemView.findViewById<MaterialCardView>(R.id.mc)

    }
}