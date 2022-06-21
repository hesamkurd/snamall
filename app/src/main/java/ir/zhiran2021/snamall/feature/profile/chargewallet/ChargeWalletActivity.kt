package ir.zhiran2021.snamall.feature.profile.chargewallet

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityChargeWalletBinding
import ir.zhiran2021.snamall.feature.profile.auoth.TokenContainer

class ChargeWalletActivity : BaseActivity() {
    lateinit var binding: ActivityChargeWalletBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_charge_wallet)
        binding = ActivityChargeWalletBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.btnWalletCharge.setOnClickListener {
            if (binding.edtWalletCharge.length() > 0) {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(
                        "http://snamall1.mamhesam.ir/v1/api/profile/wallet_chage.php?token=" + TokenContainer.token + "&wallet_charge=" + binding.edtWalletCharge.text.toString()
                            .trim()
                    )
                )
                startActivity(intent)
                finish()
            } else {
                binding.edtWalletCharge.error = "لطفا مبلغ را وارد نمایید"
            }

        }

    }
}