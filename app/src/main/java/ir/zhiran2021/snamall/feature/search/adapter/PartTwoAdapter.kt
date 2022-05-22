package ir.zhiran2021.snamall.feature.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.Part2Item
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.view.MyImageView

class PartTwoAdapter(val partTow: List<Part2Item>, val imageLoadService: ImageLoadService):
    RecyclerView.Adapter<PartTwoAdapter.PartTwoViewHolder>(){

    lateinit var onClickSearchItem: OnClickSearchItem
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartTwoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_part_two,parent,false)
        return PartTwoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PartTwoViewHolder, position: Int) {
        val partTwoItem = partTow[position]
        imageLoadService.loadImage(holder.imgSearch, partTwoItem.image)
        holder.txtName.text = partTwoItem.name

        holder.itemView.setOnClickListener {
            onClickSearchItem.onClickItemSearch(partTwoItem.id.toInt())
        }
    }

    fun setOnClickSearch(onClickSearchItem: OnClickSearchItem){
        this.onClickSearchItem = onClickSearchItem
    }

    override fun getItemCount(): Int = partTow.size

    class PartTwoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val imgSearch = itemView.findViewById<MyImageView>(R.id._img_search)
        val txtName = itemView.findViewById<TextView>(R.id.txt_search)
    }

    interface OnClickSearchItem{
        fun onClickItemSearch(productId:Int)
    }

}