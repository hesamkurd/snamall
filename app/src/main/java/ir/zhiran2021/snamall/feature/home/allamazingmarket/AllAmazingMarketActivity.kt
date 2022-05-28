package ir.zhiran2021.snamall.feature.home.allamazingmarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityAllAmazingMarketBinding
import ir.zhiran2021.snamall.feature.home.allamazingmarket.adapter.AllAmazingMarketAdapter
import ir.zhiran2021.snamall.feature.home.allamazingmarket.viewmodel.AllAmazingMarketViewModel
import ir.zhiran2021.snamall.feature.home.detailproduct.DetailActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AllAmazingMarketActivity : BaseActivity(),AllAmazingMarketAdapter.OnClickCategory {
    lateinit var binding : ActivityAllAmazingMarketBinding
    val allAmazingMarketViewModel : AllAmazingMarketViewModel by viewModel { parametersOf(0) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_all_amazing_market)
        binding = ActivityAllAmazingMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        allAmazingMarketViewModel.allAmazingMarketLiveData.observe(this){
            val allAmazingMarketAdapter: AllAmazingMarketAdapter by inject { parametersOf(it) }
            binding.rcFilter.layoutManager = LinearLayoutManager(this)
            binding.rcFilter.adapter = allAmazingMarketAdapter
            val itemDecoration : RecyclerView.ItemDecoration = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
            binding.rcFilter.addItemDecoration(itemDecoration)
            allAmazingMarketAdapter.setOnClickItemAmazing(this)

        }

        allAmazingMarketViewModel.progressBarLiveData.observe(this){
            setProgressBar(it)
        }

        allAmazingMarketViewModel.selectTitleLiveData.observe(this){
            binding.txtFilter.text = getString(it)
        }
        binding.rltvFilter.setOnClickListener {
            val dialogFilter = MaterialAlertDialogBuilder(this)
                .setSingleChoiceItems(R.array.sort,allAmazingMarketViewModel.sort){
                        dialog, index ->
                    dialog.dismiss()
                    allAmazingMarketViewModel.selectedItemDialog(index)
                }
            dialogFilter.show()
        }

    }

    override fun onClickCatItem(generalCatId: Int) {
        startActivity(Intent(this,DetailActivity::class.java).apply {
            putExtra("id",generalCatId)
        })
    }
}