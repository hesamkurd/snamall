package ir.zhiran2021.snamall.feature.cart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ProductItemItem
import ir.zhiran2021.snamall.data.ResponseCartList
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.utils.FreePercent
import ir.zhiran2021.snamall.utils.PriceConverter
import ir.zhiran2021.snamall.view.MyImageView

const val PRODUCT_ITEM = 0
const val PAY_ITEM = 1

class CartListAdapter(val cartproduct: ResponseCartList, val imageLoadService: ImageLoadService):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var publicTotalAllPrice: Int? = null
    var publicAllOffPrIce: Int? = null
    var publicAllPayablePrice: Int? = null
    lateinit var onClickRemoveItem: OnClickRemoveItem


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == PRODUCT_ITEM){
            CartProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cart,parent,false))
        }else{
            PayableViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_paybale_price,parent,false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == PRODUCT_ITEM){

            val cartProduct = cartproduct.productItem[position]
            (holder as CartProductViewHolder).txtName.text = cartProduct.name
            holder.txtColor.text = cartProduct.color
            holder.txtSize.text = cartProduct.size
            holder.txtPrice.text = PriceConverter.priceConvert(cartProduct.price)
            holder.txtCount.text = cartProduct.count
            holder.txtOffPercent.text = FreePercent.offPercent(cartProduct.offPercent.toString())
            holder.txtOffPrice.text = PriceConverter.priceConvert(cartProduct.offPrice.toString())
            holder.txtPayblePrice.text =
                PriceConverter.priceConvert(cartProduct.totalProductPrice.toString())
            imageLoadService.loadImage(holder.imgProduct,cartProduct.image)

            holder.imgRemove.setOnClickListener {
                onClickRemoveItem.onClickItem(cartProduct)
            }
            holder.imgSum.setOnClickListener {
                onClickRemoveItem.onClickSumItem(cartProduct,cartProduct.count.toInt())
            }
            holder.imgMinus.setOnClickListener {
                onClickRemoveItem.onClickMinusItem(cartProduct,cartProduct.count.toInt())
            }
        } else {
            (holder as PayableViewHolder).txtAllOffPrice.text =
                PriceConverter.priceConvert(publicAllOffPrIce.toString())
            holder.txtAllpayblaePrice.text =
                PriceConverter.priceConvert(publicAllPayablePrice.toString())
            holder.txtTotalAllPrice.text =
                PriceConverter.priceConvert(publicTotalAllPrice.toString())
        }
    }
    fun setOnClickItemRemove(onClickRemoveItem: OnClickRemoveItem){
        this.onClickRemoveItem = onClickRemoveItem
    }

    fun sumItemCount(cartItem : ProductItemItem, count: Int){

        val cartList: MutableList<ProductItemItem> = cartproduct.productItem as MutableList<ProductItemItem>
        val index = cartList.indexOf(cartItem)
        val newCount = count +1
        cartItem.count = newCount.toString()
        cartList.set(index, cartItem)
        notifyItemChanged(index)
    }
    fun minusItemCount(cartItem : ProductItemItem, count: Int){

        val cartList: MutableList<ProductItemItem> = cartproduct.productItem as MutableList<ProductItemItem>
        val index = cartList.indexOf(cartItem)
        val newCount = count - 1
        cartItem.count = newCount.toString()
        cartList.set(index, cartItem)
        notifyItemChanged(index)
    }
    override fun getItemViewType(position: Int): Int {
        return if (position == cartproduct.productItem.size){
            PAY_ITEM
        }else{
            PRODUCT_ITEM

        }
    }

    override fun getItemCount(): Int = (cartproduct.productItem.size)+1

    class CartProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val imgProduct = itemView.findViewById<MyImageView>(R.id.img_product_cart)
        val imgRemove = itemView.findViewById<ImageView>(R.id.image_remove)
        val imgSum = itemView.findViewById<ImageView>(R.id.img_sum)
        val imgMinus = itemView.findViewById<ImageView>(R.id.img_minus)
        val txtName = itemView.findViewById<TextView>(R.id.txt_name_product)
        val txtColor = itemView.findViewById<TextView>(R.id.txt_color_product)
        val txtSize = itemView.findViewById<TextView>(R.id.txt_size_product)
        val txtPrice = itemView.findViewById<TextView>(R.id.txt_price_product)
        val txtCount = itemView.findViewById<TextView>(R.id.txt_count_cart)
        val txtOffPercent = itemView.findViewById<TextView>(R.id.txt_off_percent)
        val txtOffPrice = itemView.findViewById<TextView>(R.id.txt_off_price)
        val txtPayblePrice = itemView.findViewById<TextView>(R.id.txt_payblae_price)
    }

    class PayableViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val txtTotalAllPrice = itemView.findViewById<TextView>(R.id.txt_all_totoal_price)
        val txtAllOffPrice = itemView.findViewById<TextView>(R.id.txt_all_off_price)
        val txtAllpayblaePrice = itemView.findViewById<TextView>(R.id.txt_all_payble_price)
    }

    interface OnClickRemoveItem{
        fun onClickItem(cartItem: ProductItemItem)
        fun onClickSumItem(cartItem: ProductItemItem, newCount: Int)
        fun onClickMinusItem(cartItem: ProductItemItem, newCount: Int)

    }

}