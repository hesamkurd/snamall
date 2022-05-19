package ir.zhiran2021.snamall.services

import ir.zhiran2021.snamall.view.MyImageView

interface ImageLoadService {
    fun loadImage(imageView: MyImageView, imageUrl:String?)
}