package ir.zhiran2021.snamall.feature.profile.orderdelivery.orderdeliverydetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.OrderDeliveryDetailItem
import ir.zhiran2021.snamall.data.OrderDetailItem
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.utils.PriceConverter
import ir.zhiran2021.snamall.view.MyImageView

class OrderDetailDeliveryAdapter(val images: List<OrderDeliveryDetailItem>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<OrderDetailDeliveryAdapter.DetailOrderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailOrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detils_order,parent,false)
        return DetailOrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailOrderViewHolder, position: Int) {
        val itemImageOrder = images[position]
        imageLoadService.loadImage(holder.imgOrders, itemImageOrder.image)
        holder.txtName.text = itemImageOrder.name
        holder.txtPrice.text = PriceConverter.priceConvert(itemImageOrder.price)
    }

    override fun getItemCount(): Int = images.size

    class DetailOrderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgOrders = itemView.findViewById<MyImageView>(R.id.img_detils_order)
        val txtName = itemView.findViewById<TextView>(R.id.txt_name_product_detils_order)
        val txtPrice = itemView.findViewById<TextView>(R.id.txt_price_detils_order)
    }

}