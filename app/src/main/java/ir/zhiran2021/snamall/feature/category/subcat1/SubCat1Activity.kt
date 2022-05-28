package ir.zhiran2021.snamall.feature.category.subcat1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivitySubCat1Binding
import ir.zhiran2021.snamall.feature.category.subcat1.adapter.ProductSubCat1Adapter
import ir.zhiran2021.snamall.feature.category.subcat1.viewmodel.ProductSubCat1ViewModel
import ir.zhiran2021.snamall.utils.PRODUCT_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SubCat1Activity : BaseActivity() {
    lateinit var binding: ActivitySubCat1Binding
    val productSubCat1ViewModel : ProductSubCat1ViewModel by viewModel { parametersOf(intent.getIntExtra(
        PRODUCT_ID,0)) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_sub_cat1)
        binding = ActivitySubCat1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val title = intent.getStringExtra("namesubcat1")
        binding.txtTitle.text = title
        productSubCat1ViewModel.productSubCat1LiveData.observe(this){
            val productSubCat1Adapter: ProductSubCat1Adapter by inject { parametersOf(it) }
            binding.rcSubcat1.layoutManager = LinearLayoutManager(this)
            binding.rcSubcat1.adapter = productSubCat1Adapter
            val itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL)
            binding.rcSubcat1.addItemDecoration(itemDecoration)
        }


    }
}