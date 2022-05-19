package ir.zhiran2021.snamall.feature.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponseBannerType2
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.view.MyImageView

class BannersType2Adapter(val banners: List<ResponseBannerType2>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<BannersType2Adapter.BannersViewHolder>() {

    lateinit var onClickBannerType: OnClickBannerType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_banners_type_2,parent,false)
        return BannersViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannersViewHolder, position: Int) {
        val itemBanners = banners[position]
        imageLoadService.loadImage(holder.imgBanners, itemBanners.image)
        holder.itemView.setOnClickListener {
            onClickBannerType.onClickBannerType(itemBanners.type,itemBanners.link)
        }
    }
    fun setOnClickBannersType(onClickBannerType: OnClickBannerType){
        this.onClickBannerType = onClickBannerType
    }

    override fun getItemCount(): Int = banners.size

    class BannersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgBanners = itemView.findViewById<MyImageView>(R.id.image_banners_type_2)
    }
    interface OnClickBannerType{
        fun onClickBannerType(type:String,link:String)
    }

}