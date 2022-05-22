package ir.zhiran2021.snamall.feature.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivitySearchBinding
import ir.zhiran2021.snamall.feature.category.subcat2.SubCat2Activity
import ir.zhiran2021.snamall.feature.home.detailproduct.DetailActivity
import ir.zhiran2021.snamall.feature.search.adapter.PartOneAdapter
import ir.zhiran2021.snamall.feature.search.adapter.PartTwoAdapter
import ir.zhiran2021.snamall.feature.search.viewmodel.SearchViewModel
import ir.zhiran2021.snamall.utils.PRODUCT_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SearchActivity : BaseActivity(), PartTwoAdapter.OnClickSearchItem,PartOneAdapter.OnClickItemOne {
    lateinit var binding: ActivitySearchBinding
    val searchViewModel: SearchViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_search)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgSearch.setOnClickListener {
            if (binding.edtSearch.length()>0){
                searchViewModel.getSearch(binding.edtSearch.text.toString().trim())
            }
        }
        searchViewModel.progressBarLiveData.observe(this){
            setProgressBar(it)
        }
        searchViewModel.searchLiveData.observe(this){
            binding.edtSearch.text.clear()
            if (!it.part1.isNullOrEmpty()){
                binding.txtPart1.visibility = View.VISIBLE
            }
            if (!it.part2.isNullOrEmpty()){
                binding.txtPart2.visibility = View.VISIBLE
                binding.lnrSearch.visibility = View.VISIBLE
                binding.lnrEmptySearch1.visibility = View.GONE
            }else{
                binding.lnrSearch.visibility = View.GONE
                binding.lnrEmptySearch1.visibility = View.VISIBLE
            }
            val partOneAdapter: PartOneAdapter by inject { parametersOf(it.part1) }
            binding.rcPart1.layoutManager = LinearLayoutManager(this)
            binding.rcPart1.adapter = partOneAdapter
            partOneAdapter.setOnClickPartOne(this)

            val partTwoAdapter: PartTwoAdapter by inject { parametersOf(it.part2) }
            binding.rcPart2.layoutManager  = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
            binding.rcPart2.adapter = partTwoAdapter
            partTwoAdapter.setOnClickSearch(this)
        }
    }

    override fun onClickItemSearch(productId: Int) {
        startActivity(Intent(this,DetailActivity::class.java).apply {
            putExtra("id", productId)
        })
    }

    override fun onClickItemOne(subcat2Id: Int) {
        startActivity(Intent(this,SubCat2Activity::class.java).apply {
            putExtra(PRODUCT_ID,subcat2Id)
        })
    }
}