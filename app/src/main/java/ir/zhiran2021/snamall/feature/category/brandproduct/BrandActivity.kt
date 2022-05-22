package ir.zhiran2021.snamall.feature.category.brandproduct

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginTop
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityBrandBinding
import ir.zhiran2021.snamall.feature.category.brandproduct.adapter.BrandProductAdapter
import ir.zhiran2021.snamall.feature.category.brandproduct.viewmodel.BrandBannerViewmodel
import ir.zhiran2021.snamall.feature.home.detailproduct.DetailActivity
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.utils.PRODUCT_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class BrandActivity : BaseActivity(),BrandProductAdapter.OnClickCategory {
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
            if (it.banner.isNullOrEmpty()){
                binding!!.lnrBanner.visibility = View.GONE
                val param = (binding!!.lnrIcon.layoutParams as ViewGroup.MarginLayoutParams).apply {
                    setMargins(0,0,0,0)
                }
                binding!!.lnrIcon.layoutParams = param
            }

        }

        brandBannerViewmodel.brandProductLiveData.observe(this){
            val brandProductAdapter: BrandProductAdapter by inject { parametersOf(it) }
            binding!!.rcBrandProduct.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
            binding!!.rcBrandProduct.adapter = brandProductAdapter
            val itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
            binding!!.rcBrandProduct.addItemDecoration(itemDecoration)
            brandProductAdapter.setOnClickCat(this)
        }
    }

    override fun onClickCatItem(generalCatId: Int) {
        startActivity(Intent(this, DetailActivity::class.java).apply {
            putExtra("id",generalCatId)
        })
    }
}