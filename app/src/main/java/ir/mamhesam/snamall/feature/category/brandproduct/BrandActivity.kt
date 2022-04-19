package ir.mamhesam.snamall.feature.category.brandproduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.base.BaseActivity
import ir.mamhesam.snamall.databinding.ActivityBrandBinding
import ir.mamhesam.snamall.feature.category.brandproduct.viewmodel.BrandBannerViewmodel
import ir.mamhesam.snamall.services.ImageLoadService
import ir.mamhesam.snamall.utils.PRODUCT_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class BrandActivity : BaseActivity() {
    var binding: ActivityBrandBinding?=null
    val brandBannerViewmodel: BrandBannerViewmodel by viewModel { parametersOf(intent.getStringExtra(
        PRODUCT_ID)) }
    val imageLoadService: ImageLoadService by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_brand)
        binding = ActivityBrandBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        brandBannerViewmodel.brandBannerLiveData.observe(this){
            imageLoadService.loadImage(binding!!.brandBanner,it.banner)
            imageLoadService.loadImage(binding!!.iconBrand,it.icon)
        }
    }
}