package ir.zhiran2021.snamall.feature.profile.orderdelivery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponseOrderDelivery
import ir.zhiran2021.snamall.data.ResponseOrderHistory
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.utils.PriceConverter

class OrderDeliveryAdapter(val orders: List<ResponseOrderDelivery>, val imageLoadService: ImageLoadService) :
    RecyclerView.Adapter<OrderDeliveryAdapter.OrdersViewHolder>() {

    lateinit var onClickItemOrder: OnClickItemOrderDelivery
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrdersViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {

        val itemOrders = orders[position]
        holder.txtCode.text = itemOrders.refId
        holder.txtPriceOrder.text = PriceConverter.priceConvert(itemOrders.totalPrice.toString())
        holder.txtDateOrder.text = itemOrders.dateTime
        val layoutManager =
            LinearLayoutManager(holder.rcImageOrder.context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager.initialPrefetchItemCount = itemOrders.images.size
        val imageOrderDetailAdapter = ImageOrderDeliveryAdapter(itemOrders.images, imageLoadService)
        holder.rcImageOrder.layoutManager = layoutManager
        holder.rcImageOrder.adapter = imageOrderDetailAdapter
        holder.rcImageOrder.setRecycledViewPool(viewPool)

        holder.itemView.setOnClickListener {
            onClickItemOrder.onClickOrder(itemOrders.refId)
        }

    }

    override fun getItemCount(): Int = orders.size
    fun setOnCLickAddress(onClickItemOrder: OnClickItemOrderDelivery) {
        this.onClickItemOrder = onClickItemOrder
    }

    class OrdersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtCode = itemView.findViewById<TextView>(R.id.txt_code)
        val txtPriceOrder = itemView.findViewById<TextView>(R.id.txt_price_order)
        val txtDateOrder = itemView.findViewById<TextView>(R.id.txt_date_order)
        val rcImageOrder = itemView.findViewById<RecyclerView>(R.id.rc_image_order)


    }

    interface OnClickItemOrderDelivery {
        fun onClickOrder(refId: String)
    }
}