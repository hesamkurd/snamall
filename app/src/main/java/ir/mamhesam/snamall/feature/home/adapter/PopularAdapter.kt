package ir.mamhesam.snamall.feature.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.data.ResponsePopularProduct
import ir.mamhesam.snamall.services.ImageLoadService
import ir.mamhesam.snamall.view.MyImageView

class PopularAdapter(val populars:List<ResponsePopularProduct>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_popular_product,parent,false)
        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val itemPopulars = populars[position]
        imageLoadService.loadImage(holder.imgPopularProduct,itemPopulars.image )
    }

    override fun getItemCount(): Int = populars.size

    class PopularViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val imgPopularProduct = itemView.findViewById<MyImageView>(R.id.img_popular_product)

    }
}