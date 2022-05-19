package ir.zhiran2021.snamall.feature.profile.infouser

import android.os.Bundle
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityInfoUserBinding
import ir.zhiran2021.snamall.feature.profile.infouser.viewmodel.InfoUserViewModel
import ir.zhiran2021.snamall.utils.PriceConverter
import org.koin.androidx.viewmodel.ext.android.viewModel

class InfoUserActivity : BaseActivity() {
    lateinit var binding: ActivityInfoUserBinding
    val infoUserViewModel: InfoUserViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_info_user)
        binding = ActivityInfoUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtTitle.text = "اطلاعات کاربر"
        binding.imgBack.setOnClickListener {
            finish()
        }
        infoUserViewModel.infoUserLiveData.observe(this){
            binding.apply {
                txtName.text = it.nameFamily
                txtDate.text = it.date
                txtPhone.text = it.phone
                txtWallet.text = PriceConverter.priceConvert(it.wallet)
                txtProccessing.text = it.proccesing.toString()
            }
        }
        infoUserViewModel.progressBarLiveData.observe(this){
            setProgressBar(it)
        }

    }
}