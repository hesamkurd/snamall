package ir.mamhesam.snamall.feature.home.detailproduct.property

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.base.BaseActivity
import ir.mamhesam.snamall.databinding.ActivityDescriptionBinding
import ir.mamhesam.snamall.feature.home.detailproduct.viewmodel.DetailProductViewModel
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