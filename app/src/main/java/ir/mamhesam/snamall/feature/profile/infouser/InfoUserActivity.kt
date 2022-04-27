package ir.mamhesam.snamall.feature.profile.infouser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.base.BaseActivity
import ir.mamhesam.snamall.databinding.ActivityInfoUserBinding
import ir.mamhesam.snamall.feature.profile.infouser.viewmodel.InfoUserViewModel
import ir.mamhesam.snamall.utils.PriceConverter
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