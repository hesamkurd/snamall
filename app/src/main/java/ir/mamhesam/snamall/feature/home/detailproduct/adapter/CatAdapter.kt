package ir.mamhesam.snamall.feature.home.detailproduct.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.data.ImagesItem
import ir.mamhesam.snamall.services.ImageLoadService
import ir.mamhesam.snamall.view.MyImageView

class CatAdapter(val imagesProduct: List<String>):RecyclerView.Adapter<CatAdapter.GalleryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_categories,parent,false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val itemGallery = imagesProduct[position]
        holder.imgGallery.text = itemGallery

    }

    override fun getItemCount(): Int = imagesProduct.size

    class GalleryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgGallery = itemView.findViewById<TextView>(R.id.txt_category)
    }

}