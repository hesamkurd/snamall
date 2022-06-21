package ir.zhiran2021.snamall.feature.category.generalcat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityProductGeneralBinding
import ir.zhiran2021.snamall.feature.category.generalcat.adapter.ProductGeneralCatAdapter
import ir.zhiran2021.snamall.feature.category.generalcat.viewmodel.ProductGeneralCatViewModel
import ir.zhiran2021.snamall.feature.home.detailproduct.DetailActivity
import ir.zhiran2021.snamall.utils.PRODUCT_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductGeneralActivity : BaseActivity(),ProductGeneralCatAdapter.OnClickGeneralProduct {
    lateinit var binding: ActivityProductGeneralBinding
    val productGeneralViewModel: ProductGeneralCatViewModel by viewModel { parametersOf(intent.getIntExtra(
        PRODUCT_ID,0)) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_product_general)
        binding = ActivityProductGeneralBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title = intent.getStringExtra("namecat")
        binding.txtTitle.text = title
        binding.imgBack.setOnClickListener {
            finish()
        }
        productGeneralViewModel.porductGeneralCatLiveData.observe(this){
            val productGeneralCatAdapter: ProductGeneralCatAdapter by inject { parametersOf(it) }
            binding.rcGeneralProduct.layoutManager = LinearLayoutManager(this)
            binding.rcGeneralProduct.adapter = productGeneralCatAdapter
            val itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL)
            binding.rcGeneralProduct.addItemDecoration(itemDecoration)
            productGeneralCatAdapter.setOnClickProductGeneral(this)
        }

    }

    override fun onClickGeneralProductItem(productId: Int) {
        startActivity(Intent(this,DetailActivity::class.java).apply {
            putExtra("id",productId)
        })
    }
}