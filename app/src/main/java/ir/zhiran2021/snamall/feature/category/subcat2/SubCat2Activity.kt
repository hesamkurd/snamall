package ir.zhiran2021.snamall.feature.category.subcat2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivitySubCat3Binding
import ir.zhiran2021.snamall.feature.category.subcat2.adapter.SubCat2Adapter
import ir.zhiran2021.snamall.feature.category.subcat2.viewmodel.SubCat2ViewModel
import ir.zhiran2021.snamall.feature.home.detailproduct.DetailActivity
import ir.zhiran2021.snamall.utils.PRODUCT_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SubCat2Activity : BaseActivity(), SubCat2Adapter.OnClickSubCatProduct {
    lateinit var binding: ActivitySubCat3Binding
    val subCat2ViewModel: SubCat2ViewModel by viewModel { parametersOf(intent.getIntExtra(PRODUCT_ID,0)) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_sub_cat3)
        binding = ActivitySubCat3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val title = intent.getStringExtra("namesubcat2")
        binding.txtTitle.text = title

        subCat2ViewModel.subCat2LiveData.observe(this){
            val subCat2Adapter: SubCat2Adapter by inject { parametersOf(it) }
            binding.rcSubcat2.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            binding.rcSubcat2.adapter = subCat2Adapter
            val itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL)
            binding.rcSubcat2.addItemDecoration(itemDecoration)
            subCat2Adapter.setOnClickProductItem(this)
        }

    }

    override fun onClickSubCatItem(productId: Int) {
        startActivity(Intent(this, DetailActivity::class.java).apply {
            putExtra("id",productId)
        })
    }
}