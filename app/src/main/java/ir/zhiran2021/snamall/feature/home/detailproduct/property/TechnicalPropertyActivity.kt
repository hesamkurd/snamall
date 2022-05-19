package ir.zhiran2021.snamall.feature.home.detailproduct.property

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityTechnicalPropertyBinding
import ir.zhiran2021.snamall.feature.home.detailproduct.property.adapter.TechnicalPropertyAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TechnicalPropertyActivity : BaseActivity() {
    var binding : ActivityTechnicalPropertyBinding?=null
    val propertyViewModel: PropertyViewModel by viewModel { parametersOf(intent.getIntExtra("id",0)) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_technical_property)
        binding = ActivityTechnicalPropertyBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        propertyViewModel.technicalPropertyLiveData.observe(this){
            val technicalPropertyAdapter: TechnicalPropertyAdapter by inject { parametersOf(it) }
            binding!!.rcProductProperty.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
            binding!!.rcProductProperty.adapter = technicalPropertyAdapter
        }

        propertyViewModel.progressBarLiveData.observe(this){
            setProgressBar(it)
        }
    }
}