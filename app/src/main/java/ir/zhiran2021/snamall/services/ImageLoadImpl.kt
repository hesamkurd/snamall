package ir.zhiran2021.snamall.services

import com.facebook.drawee.view.SimpleDraweeView
import ir.zhiran2021.snamall.view.MyImageView

class ImageLoadImpl: ImageLoadService {
    override fun loadImage(imageView: MyImageView, imageUrl: String?) {
        if (imageView is SimpleDraweeView){
            imageView.setImageURI(imageUrl)
        }
    }
}