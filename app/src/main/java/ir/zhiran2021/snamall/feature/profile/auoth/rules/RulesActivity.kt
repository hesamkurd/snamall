package ir.zhiran2021.snamall.feature.profile.auoth.rules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityRulesBinding
import ir.zhiran2021.snamall.feature.profile.auoth.rules.adapter.RulesAdapter
import ir.zhiran2021.snamall.feature.profile.auoth.rules.viewmodel.RulesViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RulesActivity : BaseActivity() {
    lateinit var binding: ActivityRulesBinding
    val rulesViewModel: RulesViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_rules)
        binding = ActivityRulesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtTitle.text = "قوانین و شرایط"
        binding.imgBack.setOnClickListener {
            finish()
        }

        rulesViewModel.rulesLiveData.observe(this){
            val rulesAdapter: RulesAdapter by inject { parametersOf(it) }
            binding.rcRules.layoutManager = LinearLayoutManager(this)
            binding.rcRules.adapter = rulesAdapter
        }

    }
}