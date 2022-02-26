package ir.mamhesam.snamall.services

import android.widget.ImageView
import ir.mamhesam.snamall.view.MyImageView

interface ImageLoadService {
    fun loadImage(imageView: MyImageView, imageUrl:String?)
}