package ir.mamhesam.snamall.feature.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.data.ResponseBanners
import ir.mamhesam.snamall.services.ImageLoadService
import ir.mamhesam.snamall.view.MyImageView

class BannersAdapter(val banners: List<ResponseBanners>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<BannersAdapter.BannersViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_banners,parent,false)
        return BannersViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannersViewHolder, position: Int) {
        val itemBanners = banners[position]
        imageLoadService.loadImage(holder.imgBanners, itemBanners.image)
    }

    override fun getItemCount(): Int = banners.size

    class BannersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgBanners = itemView.findViewById<MyImageView>(R.id.image_banners)
    }

}