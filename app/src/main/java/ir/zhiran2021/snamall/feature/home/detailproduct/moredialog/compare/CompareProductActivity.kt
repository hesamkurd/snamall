package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityCompareProductBinding
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.adapter.CompareParentAdapter
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.viewmodel.CompareProductViewModel
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.utils.PriceConverter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CompareProductActivity : BaseActivity() {
    lateinit var binding: ActivityCompareProductBinding
    val compareProductViewModel: CompareProductViewModel by viewModel { parametersOf(intent.extras!!) }
    val imageLoadService:ImageLoadService by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_compare_product)
        binding = ActivityCompareProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtTitle.text = "مقایسه مجصول"

        compareProductViewModel.compareProductLiveData.observe(this){
            binding.apply {
                txtPriceProductOne.text = PriceConverter.priceConvert(it.price1)
                txtPriceProductTwo.text = PriceConverter.priceConvert(it.price2)
                txtNamePeroductOne.text = it.name1
                txtNamePeroductTwo.text = it.name2
                imageLoadService.loadImage(binding.imgPrOne,it.imageurl1)
                imageLoadService.loadImage(binding.imgPrTwo,it.imageurl2)

            }
            val compareParentAdapter: CompareParentAdapter by inject { parametersOf(it.property) }
            binding.rcComapreProperty.layoutManager = LinearLayoutManager(this)
            binding.rcComapreProperty.adapter = compareParentAdapter
        }
    }
}