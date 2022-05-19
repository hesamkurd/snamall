package ir.zhiran2021.snamall.feature.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponsePopularProduct
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.view.MyImageView

class PopularAdapter(val populars:List<ResponsePopularProduct>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    lateinit var onClickPopularItem: OnClickPopularItem
    fun setOnPopularClick(onClickPopularItem: OnClickPopularItem){
        this.onClickPopularItem = onClickPopularItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_popular_product,parent,false)
        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val itemPopulars = populars[position]
        Picasso.get().load(itemPopulars.image).into(holder.imgPopularProduct)
        holder.itemView.setOnClickListener {
            onClickPopularItem.onClickPopular(itemPopulars.id)
        }
    }

    override fun getItemCount(): Int = populars.size

    class PopularViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val imgPopularProduct = itemView.findViewById<ImageView>(R.id.img_popular_product)

    }

    interface OnClickPopularItem{
        fun onClickPopular(productId:Int)
    }
}