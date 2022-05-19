package ir.zhiran2021.snamall.feature.home.adapter

import android.graphics.Paint
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponseAmazingProducts
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.utils.FreePercent
import ir.zhiran2021.snamall.utils.PriceConverter
import ir.zhiran2021.snamall.view.MyImageView

const val FIRST_AMAZING = 1
const val PRODUCT_AMAZING = 2
const val LAST_AMAZING = 3

class AmazingAdapter(
    val amazingProducts: List<ResponseAmazingProducts>,
    val imageLoadService: ImageLoadService
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var onClickProduct: OnClickProduct
    lateinit var onClickLast: OnClickLast
    fun setOnClickProductItem(onClickProduct: OnClickProduct){
        this.onClickProduct = onClickProduct
    }
    fun setOnClickItemLast(onClickLast: OnClickLast){
        this.onClickLast = onClickLast
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FIRST_AMAZING -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_first_amazing, parent, false)
                FirstAmazingViewHolder(view)
            }
            PRODUCT_AMAZING -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_amazing_product, parent, false)
                AmazingProductsViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_last_amazing, parent, false)
                LastAmazingViewHolder(view)
            }

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            getItemViewType(position) == FIRST_AMAZING -> {
                (holder as FirstAmazingViewHolder).imgFirstAmazingProducts.setActualImageResource(R.drawable.produt_item_image)
            }

            getItemViewType(position) == PRODUCT_AMAZING -> {
                val itemAmazing = amazingProducts[position - 1]
                (holder as AmazingProductsViewHolder).txtPriceAmazingProducts.text =
                    PriceConverter.priceConvert(itemAmazing.price.toString())
                holder.txtPriceAmazingProducts.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                holder.txtFreeAmazingProducts.text =FreePercent.offPercent(itemAmazing.offPercent.toString())
                holder.txtFreePriceAmazingProducts.text =PriceConverter.priceConvert(itemAmazing.amazingPrice.toString())
                holder.txtTitleAmazingProducts.text = itemAmazing.name
                holder.txtCountAmazingProducts.text =PriceConverter.sellsCount(itemAmazing.sellsCount.toString())
                holder.txtTimeAmazingProducts.text = itemAmazing.amazingTime.toString()
                holder.prAmazung.max = itemAmazing.number
                holder.prAmazung.progress = itemAmazing.sellsCount
                imageLoadService.loadImage(holder.imgAmazingProducts, itemAmazing.image)
                val countDownTimer: CountDownTimer =
                    object : CountDownTimer(itemAmazing.amazingTime, 1000) {
                        override fun onTick(p0: Long) {
                            val hour = p0 / 3600000 % 24
                            val min = p0 / 60000 % 60
                            val sec = p0 / 1000 % 60
                            holder.txtTimeAmazingProducts.text =
                                holder.itemView.context.getString(R.string.time,hour, min, sec)
                        }

                        override fun onFinish() {
                            holder.txtTimeAmazingProducts.text = "تخفیف تمام شد"
                        }

                    }
                countDownTimer.start()

                holder.itemView.setOnClickListener {
                    onClickProduct.onClickProduct(itemAmazing.id)
                }
            }
            getItemViewType(position) == LAST_AMAZING -> {
                (holder as LastAmazingViewHolder).imaLastAmazing.setImageResource(R.drawable.ic_arrow)
                holder.txtLastAmazing.text = "مشاهده همه"
                holder.mterialCart.setOnClickListener {
                    onClickLast.onClickLastItem()
                }
            }

        }
    }

    override fun getItemCount(): Int = (amazingProducts.size) + 2

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> {
                FIRST_AMAZING
            }
            6 -> {
                LAST_AMAZING
            }
            else->{

                PRODUCT_AMAZING
            }

        }
    }

    class FirstAmazingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFirstAmazingProducts = itemView.findViewById<MyImageView>(R.id.img_first_amazing)

    }

    class AmazingProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgAmazingProducts = itemView.findViewById<MyImageView>(R.id.img_amazing_product)
        val txtTitleAmazingProducts = itemView.findViewById<TextView>(R.id.txt_title_product)
        val txtFreeAmazingProducts = itemView.findViewById<TextView>(R.id.txt_free)
        val txtPriceAmazingProducts = itemView.findViewById<TextView>(R.id.txt_price)
        val txtFreePriceAmazingProducts = itemView.findViewById<TextView>(R.id.txt_free_price)
        val txtCountAmazingProducts = itemView.findViewById<TextView>(R.id.txt_count)
        val txtTimeAmazingProducts = itemView.findViewById<TextView>(R.id.txt_time)
        val prAmazung = itemView.findViewById<ProgressBar>(R.id.progress_amazing)

    }

    class LastAmazingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imaLastAmazing = itemView.findViewById<ImageView>(R.id.img_last_amazing)
        val txtLastAmazing = itemView.findViewById<TextView>(R.id.txt_last_amazing)
        val mterialCart = itemView.findViewById<LinearLayout>(R.id.lnr_last_item)

    }

    interface OnClickProduct{
        fun onClickProduct(productId: Int)
    }

    interface OnClickLast{
        fun onClickLastItem()
    }

}