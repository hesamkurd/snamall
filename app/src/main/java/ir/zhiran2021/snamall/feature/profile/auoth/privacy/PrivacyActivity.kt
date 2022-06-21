package ir.zhiran2021.snamall.feature.profile.auoth.privacy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityPrivacyBinding
import ir.zhiran2021.snamall.feature.profile.auoth.privacy.adapter.PrivacyAdapter
import ir.zhiran2021.snamall.feature.profile.auoth.privacy.viewmodel.PrivacyViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PrivacyActivity : BaseActivity() {
    lateinit var binding: ActivityPrivacyBinding
    val privacyViewModel: PrivacyViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_privacy)
        binding = ActivityPrivacyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.txtTitle.text = "حریم خصوصی"
        binding.imgBack.setOnClickListener {
            finish()
        }
        privacyViewModel.privacyLivaData.observe(this){
            val privacyAdapter: PrivacyAdapter by inject { parametersOf(it) }
            binding.rcPrivacy.layoutManager = LinearLayoutManager(this)
            binding.rcPrivacy.adapter = privacyAdapter
        }

    }
}