package ir.mamhesam.snamall.feature.home.allamazing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.base.BaseActivity
import ir.mamhesam.snamall.databinding.ActivityAllAmazingBinding
import ir.mamhesam.snamall.feature.home.allamazing.adapter.AllAmazingAdapter
import ir.mamhesam.snamall.feature.home.allamazing.viewmodel.AllAmazingViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AllAmazingActivity : BaseActivity() {
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
}