package ir.zhiran2021.snamall.feature.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.Part1Item

class PartOneAdapter(val partOne: List<Part1Item>):RecyclerView.Adapter<PartOneAdapter.PartOneViewHolder>() {

    lateinit var onClickItemOne: OnClickItemOne


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartOneViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_part_one,parent,false)
        return PartOneViewHolder(view)
    }

    override fun onBindViewHolder(holder: PartOneViewHolder, position: Int) {
        val searchItem = partOne[position]
        holder.txtCategory.text = searchItem.title

        holder.itemView.setOnClickListener {
            onClickItemOne.onClickItemOne(searchItem.catId.toInt())
        }
    }

    fun setOnClickPartOne(onClickItemOne: OnClickItemOne){
        this.onClickItemOne = onClickItemOne
    }

    override fun getItemCount(): Int = partOne.size

    class PartOneViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val txtCategory = itemView.findViewById<TextView>(R.id.txt_part_one)
    }

    interface OnClickItemOne{
        fun onClickItemOne(subcat2Id:Int)
    }
}