package ir.mamhesam.snamall.feature.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.data.Part2Item
import ir.mamhesam.snamall.services.ImageLoadService
import ir.mamhesam.snamall.view.MyImageView

class PartTwoAdapter(val partTow: List<Part2Item>, val imageLoadService: ImageLoadService):
    RecyclerView.Adapter<PartTwoAdapter.PartTwoViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartTwoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_part_two,parent,false)
        return PartTwoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PartTwoViewHolder, position: Int) {
        val partTwoItem = partTow[position]
        imageLoadService.loadImage(holder.imgSearch, partTwoItem.image)
        holder.txtName.text = partTwoItem.name
    }

    override fun getItemCount(): Int = partTow.size

    class PartTwoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val imgSearch = itemView.findViewById<MyImageView>(R.id._img_search)
        val txtName = itemView.findViewById<TextView>(R.id.txt_search)
    }

}