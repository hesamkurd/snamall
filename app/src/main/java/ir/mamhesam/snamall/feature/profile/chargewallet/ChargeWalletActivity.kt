package ir.mamhesam.snamall.feature.profile.chargewallet

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.base.BaseActivity
import ir.mamhesam.snamall.databinding.ActivityChargeWalletBinding
import ir.mamhesam.snamall.feature.profile.auoth.TokenContainer
import ir.mamhesam.snamall.utils.PriceConverter

class ChargeWalletActivity : BaseActivity() {
    lateinit var binding: ActivityChargeWalletBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_charge_wallet)
        binding = ActivityChargeWalletBinding.inflate(layoutInflater)
        setContentView(binding.root)



            binding.btnWalletCharge.setOnClickListener {
                if(binding.edtWalletCharge.length()>0){
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://snamall1.mamhesam.ir/v1/api/profile/wallet_chage.php?token="+TokenContainer.token+"&wallet_charge="+binding.edtWalletCharge.text.toString().trim()))
                    startActivity(intent)
                    finish()
                }else{
                    binding.edtWalletCharge.error = "لطفا مبلغ را وارد نمایید"
                }

            }

    }
}