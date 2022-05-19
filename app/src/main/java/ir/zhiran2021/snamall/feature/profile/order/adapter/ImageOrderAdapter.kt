package ir.zhiran2021.snamall.feature.profile.order.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ImagesItemOrder
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.view.MyImageView

class ImageOrderAdapter(val images: List<ImagesItemOrder>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<ImageOrderAdapter.ImageOrderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageOrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_order,parent,false)
        return ImageOrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageOrderViewHolder, position: Int) {
        val itemImageOrder = images[position]
        imageLoadService.loadImage(holder.imgOrders, itemImageOrder.url)
    }

    override fun getItemCount(): Int = images.size

    class ImageOrderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgOrders = itemView.findViewById<MyImageView>(R.id.img_order)
    }

}