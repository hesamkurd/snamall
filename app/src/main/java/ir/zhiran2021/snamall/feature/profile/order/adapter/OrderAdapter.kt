package ir.zhiran2021.snamall.feature.profile.order.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponseOrderHistory
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.utils.PriceConverter

class OrderAdapter(val orders: List<ResponseOrderHistory>,val imageLoadService: ImageLoadService) :
    RecyclerView.Adapter<OrderAdapter.OrdersViewHolder>() {

    lateinit var onClickItemOrder: OnClickItemOrder
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
        layoutManager.initialPrefetchItemCount = itemOrders.imagesOrder.size
        val imageOrderAdapter = ImageOrderAdapter(itemOrders.imagesOrder, imageLoadService)
        holder.rcImageOrder.layoutManager = layoutManager
        holder.rcImageOrder.adapter = imageOrderAdapter
        holder.rcImageOrder.setRecycledViewPool(viewPool)

        holder.itemView.setOnClickListener {
            onClickItemOrder.onClickOrder(itemOrders.refId)
        }

    }

    override fun getItemCount(): Int = orders.size
    fun setOnCLickAddress(onClickItemOrder: OnClickItemOrder) {
        this.onClickItemOrder = onClickItemOrder
    }

    class OrdersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtCode = itemView.findViewById<TextView>(R.id.txt_code)
        val txtPriceOrder = itemView.findViewById<TextView>(R.id.txt_price_order)
        val txtDateOrder = itemView.findViewById<TextView>(R.id.txt_date_order)
        val rcImageOrder = itemView.findViewById<RecyclerView>(R.id.rc_image_order)


    }

    interface OnClickItemOrder {
        fun onClickOrder(refId: String)
    }
}