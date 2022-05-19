package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityCompareCategoryBinding
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.adapter.CompareCategoryAdapter
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.viewmodel.CompareCatViewModel
import ir.zhiran2021.snamall.utils.ID_ONE
import ir.zhiran2021.snamall.utils.ID_TWO
import ir.zhiran2021.snamall.utils.PRODUCT_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CompareCategoryActivity : BaseActivity(),CompareCategoryAdapter.OnClickCategoryItem {
    val compareViewModel: CompareCatViewModel by viewModel { parametersOf(intent.getIntExtra(
        PRODUCT_ID,0)) }
    var idOne:Int?=null
    var idTwo:Int?=null
    lateinit var binding: ActivityCompareCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_compare_category)
        binding = ActivityCompareCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idOne = intent.getIntExtra(PRODUCT_ID,0)
        binding.txtTitle.text = "دسته بندی محصولات"

        compareViewModel.progressBarLiveData.observe(this){
            setProgressBar(it)
        }
        compareViewModel.compareCatLiveData.observe(this){
            val compareCategoryAdapter : CompareCategoryAdapter by inject { parametersOf(it) }
            binding.rcCompareCategory.layoutManager = LinearLayoutManager(this)
            binding.rcCompareCategory.adapter = compareCategoryAdapter
            compareCategoryAdapter.setonClickCategoryItem(this)
        }
    }

    override fun onClickCategory(productIdTwo: Int) {
        idTwo = productIdTwo
        startActivity(Intent(this,CompareProductActivity::class.java).apply {
            putExtra(ID_ONE,idOne)
            putExtra(ID_TWO,idTwo)
        })
    }
}