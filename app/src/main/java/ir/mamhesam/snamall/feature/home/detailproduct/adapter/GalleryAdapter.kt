package ir.mamhesam.snamall.feature.home.detailproduct.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.data.ImagesItem
import ir.mamhesam.snamall.data.ImagesItemOrder
import ir.mamhesam.snamall.services.ImageLoadService
import ir.mamhesam.snamall.view.MyImageView

class GalleryAdapter(val imagesProductOrder: List<ImagesItem>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_images,parent,false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val itemGallery = imagesProductOrder[position]
        Picasso.get().load(itemGallery.image).into(holder.imgGallery)
    }

    override fun getItemCount(): Int = imagesProductOrder.size

    class GalleryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgGallery = itemView.findViewById<MyImageView>(R.id.img_gallery)
    }

}