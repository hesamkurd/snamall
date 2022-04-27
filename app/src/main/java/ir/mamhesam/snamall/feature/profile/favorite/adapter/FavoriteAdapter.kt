package ir.mamhesam.snamall.feature.profile.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.data.ResponseFavorite
import ir.mamhesam.snamall.services.ImageLoadService
import ir.mamhesam.snamall.utils.FreePercent
import ir.mamhesam.snamall.utils.PriceConverter
import ir.mamhesam.snamall.view.MyImageView

class FavoriteAdapter(val favorite: List<ResponseFavorite>, val imageLoadService: ImageLoadService):RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    lateinit var onClickFavorite: OnClickFavorite

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favourite,parent,false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val itemCategories = favorite[position]

        imageLoadService.loadImage(holder.imgFavorite,itemCategories.url)
        holder.txtNameFavorite.text = itemCategories.name

        holder.itemView.setOnClickListener {
            onClickFavorite.onClickFavoriteItem(itemCategories.id.toInt())
        }

    }

    fun setOnClickFavoriteItem(onClickFavorite: OnClickFavorite){
        this.onClickFavorite = onClickFavorite
    }


    override fun getItemCount(): Int = favorite.size

    class FavoriteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgFavorite = itemView.findViewById<MyImageView>(R.id.img_favourites)
        val txtNameFavorite = itemView.findViewById<TextView>(R.id.txt_name_favourite)

    }

    interface OnClickFavorite{
        fun onClickFavoriteItem(id:Int)
    }

}