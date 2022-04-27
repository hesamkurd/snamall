package ir.mamhesam.snamall.feature.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.base.BaseActivity
import ir.mamhesam.snamall.databinding.ActivitySearchBinding
import ir.mamhesam.snamall.feature.search.adapter.PartOneAdapter
import ir.mamhesam.snamall.feature.search.adapter.PartTwoAdapter
import ir.mamhesam.snamall.feature.search.viewmodel.SearchViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SearchActivity : BaseActivity() {
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
        searchViewModel.searchLiveData.observe(this){
            binding.edtSearch.text.clear()
            if (!it.part1.isNullOrEmpty()){
                binding.txtPart1.visibility = View.VISIBLE
            }
            if (!it.part2.isNullOrEmpty()){
                binding.txtPart2.visibility = View.VISIBLE
            }
            val partOneAdapter: PartOneAdapter by inject { parametersOf(it.part1) }
            binding.rcPart1.layoutManager = LinearLayoutManager(this)
            binding.rcPart1.adapter = partOneAdapter

            val partTwoAdapter: PartTwoAdapter by inject { parametersOf(it.part2) }
            binding.rcPart2.layoutManager  = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
            binding.rcPart2.adapter = partTwoAdapter
        }
    }
}