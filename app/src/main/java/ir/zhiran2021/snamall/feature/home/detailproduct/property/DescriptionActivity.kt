package ir.zhiran2021.snamall.feature.home.detailproduct.property

import android.os.Bundle
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityDescriptionBinding
import ir.zhiran2021.snamall.feature.home.detailproduct.viewmodel.DetailProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DescriptionActivity : BaseActivity() {
    var binding: ActivityDescriptionBinding?=null
    var idProduct: Int?=null
    val detailProductViewModel: DetailProductViewModel by viewModel {
        parametersOf(
            intent.getIntExtra(
                "id",
                0
            )
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_description)
        binding= ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        detailProductViewModel.detailProductLiveData.observe(this){

            binding!!.txtDescription.text = it.description
        }
    }
}