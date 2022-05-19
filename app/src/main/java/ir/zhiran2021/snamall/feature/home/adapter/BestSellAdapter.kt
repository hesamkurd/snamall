package ir.zhiran2021.snamall.feature.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponseBanners
import ir.zhiran2021.snamall.data.ResponseBestSellProduct
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.view.MyImageView

class BestSellAdapter(val banners: List<ResponseBestSellProduct>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<BestSellAdapter.BannersViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_best_sell,parent,false)
        return BannersViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannersViewHolder, position: Int) {
        val itemBanners = banners[position]
        imageLoadService.loadImage(holder.imgBestSell, itemBanners.image)
        Picasso.get().load(itemBanners.number).into(holder.imgNumBest)
        holder.txtTitle.text = itemBanners.name
    }

    override fun getItemCount(): Int = banners.size

    class BannersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgBestSell = itemView.findViewById<MyImageView>(R.id.img_best_sell)
        val imgNumBest = itemView.findViewById<ImageView>(R.id.img_num_best)
        val txtTitle = itemView.findViewById<TextView>(R.id.txt_title_best_sell)
    }

}