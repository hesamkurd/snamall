package ir.zhiran2021.snamall.feature.home.detailproduct.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.SimilarProductItem
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.view.MyImageView

class SimilarAdapter(val similars: List<SimilarProductItem>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<SimilarAdapter.SimilarViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_similar,parent,false)
        return SimilarViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimilarViewHolder, position: Int) {
        val similarItem = similars[position]
        imageLoadService.loadImage(holder.imgSimilar,similarItem.url)
        holder.txtName.text = similarItem.name
    }

    override fun getItemCount(): Int = similars.size

    class SimilarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgSimilar = itemView.findViewById<MyImageView>(R.id.img_similar)
        val txtName = itemView.findViewById<TextView>(R.id.txt_similar)
    }

}