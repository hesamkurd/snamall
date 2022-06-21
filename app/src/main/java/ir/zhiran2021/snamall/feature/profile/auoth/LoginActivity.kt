package ir.zhiran2021.snamall.feature.profile.auoth

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import ir.zhiran2021.snamall.data.ResponseLogin
import ir.zhiran2021.snamall.databinding.ActivityLoginBinding
import ir.zhiran2021.snamall.feature.profile.auoth.privacy.PrivacyActivity
import ir.zhiran2021.snamall.feature.profile.auoth.rules.RulesActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    var binding: ActivityLoginBinding? = null
    val authViewModel: AuthViewModel by viewModel()
    var items: ResponseLogin? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.textPrivacy.setOnClickListener {
            startActivity(Intent(this,PrivacyActivity::class.java))
        }
        binding!!.textRules.setOnClickListener {
            startActivity(Intent(this,RulesActivity::class.java))
        }
        binding!!.textPrivacy.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding!!.textRules.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding!!.btnLogin.setOnClickListener {

            when{
                binding!!.edtLogin.length() == 0 ->{
                    binding!!.edtLogin.error = "شماره تلفن را وارد نمایید"
                }

                else->{
                    val phone = binding!!.edtLogin.text.toString()
                    authViewModel.checkUser(phone)
                }
            }

        }

        authViewModel.checkUserLiveData.observe(this){
            if (it.message == "2"){
                startActivity(Intent(this,VerifyLoginActivity::class.java).apply {
                    putExtra("phone", binding!!.edtLogin.text.toString())
                })
                finish()
            }else{
                Snackbar.make(binding!!.coordinator,it.message,Snackbar.LENGTH_LONG).show()
            }
        }

        binding!!.txtIntentRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }


    }
}