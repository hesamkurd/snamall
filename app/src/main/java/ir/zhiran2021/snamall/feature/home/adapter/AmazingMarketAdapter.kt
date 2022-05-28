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
import ir.zhiran2021.snamall.data.ResponseAmazingMarket
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.utils.FreePercent
import ir.zhiran2021.snamall.utils.PriceConverter
import ir.zhiran2021.snamall.view.MyImageView

const val FIRST_AMAZING_MARKET = 1
const val PRODUCT_AMAZING_MARKET = 2
const val LAST_AMAZING_MARKET = 3

class AmazingMarketAdapter(
    val amazingProduct: List<ResponseAmazingMarket>,
    val imageLoadService: ImageLoadService
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var onClickAmazingProductMarket: OnClickAmazingProductMarket
    lateinit var onClickLastMarket: OnClickLastMarket

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FIRST_AMAZING_MARKET -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_first_amazing_market, parent, false)
                FirstAmazingMarketViewHolder(view)
            }

            PRODUCT_AMAZING_MARKET -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_amazing_product_market, parent, false)
                ProductAmazingMarketViewHolder(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_last_amazing_market, parent, false)
                LastAmazingMarketViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            getItemViewType(position) == FIRST_AMAZING_MARKET -> {
                (holder as FirstAmazingMarketViewHolder).imgFirstAmazing.setActualImageResource(R.drawable.amazing1)
            }

            getItemViewType(position) == PRODUCT_AMAZING_MARKET -> {
                val itemAmazing = amazingProduct[position - 1]
                (holder as ProductAmazingMarketViewHolder).txtPriceAmazingProducts.text =
                    PriceConverter.priceConvert(itemAmazing.price.toString())
                holder.txtPriceAmazingProducts.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                holder.txtFreeAmazingProducts.text = FreePercent.offPercent(itemAmazing.offPercent.toString())
                holder.txtFreePriceAmazingProducts.text = PriceConverter.priceConvert(itemAmazing.amazingPrice.toString())
                holder.txtTitleAmazingProducts.text = itemAmazing.name
                holder.txtCountAmazingProducts.text = PriceConverter.sellsCount(itemAmazing.sellsCount.toString())
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
                    onClickAmazingProductMarket.onClickMarketAmazing(itemAmazing.id)
                }
            }
            getItemViewType(position) == LAST_AMAZING_MARKET -> {
                (holder as LastAmazingMarketViewHolder).imaLastAmazing.setImageResource(R.drawable.ic_arrow)
                holder.txtLastAmazing.text = "مشاهده همه"
                holder.mterialCart.setOnClickListener {
                    onClickLastMarket.onClickLastItemMarket()
                }
            }

        }
    }

    fun setOnClickProductAmazing(onClickAmazingProductMarket: OnClickAmazingProductMarket){
        this.onClickAmazingProductMarket = onClickAmazingProductMarket
    }

    fun setOnClickItemLastMarket(onClickLastMarket: OnClickLastMarket){
        this.onClickLastMarket = onClickLastMarket
    }

    override fun getItemCount(): Int = (amazingProduct.size) + 2

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> {
                FIRST_AMAZING_MARKET
            }
            6 -> {
                LAST_AMAZING_MARKET
            }
            else->{

                PRODUCT_AMAZING_MARKET
            }

        }

    }

    class FirstAmazingMarketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFirstAmazing = itemView.findViewById<MyImageView>(R.id.img_first_amazing_market)

    }

    class ProductAmazingMarketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgAmazingProducts = itemView.findViewById<MyImageView>(R.id.img_amazing_product_market)
        val txtTitleAmazingProducts = itemView.findViewById<TextView>(R.id.txt_title_product_market)
        val txtFreeAmazingProducts = itemView.findViewById<TextView>(R.id.txt_free_market)
        val txtPriceAmazingProducts = itemView.findViewById<TextView>(R.id.txt_price_market)
        val txtFreePriceAmazingProducts = itemView.findViewById<TextView>(R.id.txt_free_price_market)
        val txtCountAmazingProducts = itemView.findViewById<TextView>(R.id.txt_count_market)
        val txtTimeAmazingProducts = itemView.findViewById<TextView>(R.id.txt_time_market)
        val prAmazung = itemView.findViewById<ProgressBar>(R.id.progress_amazing_market)
    }

    class LastAmazingMarketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imaLastAmazing = itemView.findViewById<ImageView>(R.id.img_last_amazing_market)
        val txtLastAmazing = itemView.findViewById<TextView>(R.id.txt_last_amazing_market)
        val mterialCart = itemView.findViewById<LinearLayout>(R.id.lnr_last_item_market)
    }

    interface OnClickAmazingProductMarket{
        fun onClickMarketAmazing(productId:Int)
    }

    interface OnClickLastMarket{
        fun onClickLastItemMarket()
    }
}