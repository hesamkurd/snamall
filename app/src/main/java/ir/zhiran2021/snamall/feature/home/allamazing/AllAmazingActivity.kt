package ir.zhiran2021.snamall.feature.home.allamazing

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityAllAmazingBinding
import ir.zhiran2021.snamall.feature.home.allamazing.adapter.AllAmazingAdapter
import ir.zhiran2021.snamall.feature.home.allamazing.viewmodel.AllAmazingViewModel
import ir.zhiran2021.snamall.feature.home.detailproduct.DetailActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AllAmazingActivity : BaseActivity() , AllAmazingAdapter.OnClickCategory{
    lateinit var binding : ActivityAllAmazingBinding
    val allAmazingViewModel: AllAmazingViewModel by viewModel { parametersOf(0) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_all_amazing)
        binding = ActivityAllAmazingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        allAmazingViewModel.allAmazingLiveData.observe(this){
            val allAmazingAdapter: AllAmazingAdapter by inject { parametersOf(it) }
            binding.rcFilter.layoutManager = LinearLayoutManager(this)
            binding.rcFilter.adapter = allAmazingAdapter
            val itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL)
            binding.rcFilter.addItemDecoration(itemDecoration)
            allAmazingAdapter.setOnClickItemAmazing(this)
        }
        allAmazingViewModel.progressBarLiveData.observe(this){
            setProgressBar(it)
        }
        allAmazingViewModel.selectTitleLiveData.observe(this){
            binding.txtFilter.text = getString(it)
        }

        binding.rltvFilter.setOnClickListener {
            val dialogFilter = MaterialAlertDialogBuilder(this)
                .setSingleChoiceItems(R.array.sort,allAmazingViewModel.sort){
                    dialog, index ->
                    dialog.dismiss()
                    allAmazingViewModel.selectedItemDialog(index)
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