package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare

import android.os.Bundle
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityProductCompareBinding
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.viewmodel.CompareProductViewModel
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.utils.PriceConverter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class ProductCompareActivity : BaseActivity() {
    lateinit var binding: ActivityProductCompareBinding
    val compareProductViewModel: CompareProductViewModel by viewModel { parametersOf(intent.extras!!) }
    val imageLoadService: ImageLoadService by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_product_compare)
        binding = ActivityProductCompareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        compareProductViewModel.compareProductLiveData.observe(this){
            Timber.e("$it")
            binding.apply {
                txtPriceProductOne.text = PriceConverter.priceConvert(it.price1)
                txtPriceProductTwo.text = PriceConverter.priceConvert(it.price2)
                txtNamePeroductOne.text = it.name1
                txtNamePeroductTwo.text = it.name2
                imageLoadService.loadImage(binding.imgPrOne,it.imageurl1)
                imageLoadService.loadImage(binding.imgPrTwo,it.imageurl2)

            }
        }
    }
}