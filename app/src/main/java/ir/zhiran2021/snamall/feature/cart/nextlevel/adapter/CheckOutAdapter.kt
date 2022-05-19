package ir.zhiran2021.snamall.feature.cart.nextlevel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ProductItemDeliveriesItem
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.view.MyImageView

class CheckOutAdapter(val checkOut: List<ProductItemDeliveriesItem>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<CheckOutAdapter.BannersViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_checkout_product,parent,false)
        return BannersViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannersViewHolder, position: Int) {
        val itemBanners = checkOut[position]
        imageLoadService.loadImage(holder.imgProdcut, itemBanners.iamge)
        holder.txtNameProduct.text = itemBanners.name
        holder.txtCount.text = itemBanners.count
    }

    override fun getItemCount(): Int = checkOut.size

    class BannersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgProdcut = itemView.findViewById<MyImageView>(R.id.img_product)
        val txtNameProduct = itemView.findViewById<TextView>(R.id.txt_name_product)
        val txtCount = itemView.findViewById<TextView>(R.id.txt_count_product)
    }

}