package ir.mamhesam.snamall.services

import com.facebook.drawee.view.SimpleDraweeView
import ir.mamhesam.snamall.view.MyImageView

class ImageLoadImpl: ImageLoadService {
    override fun loadImage(imageView: MyImageView, imageUrl: String?) {
        if (imageView is SimpleDraweeView){
            imageView.setImageURI(imageUrl)
        }
    }
}