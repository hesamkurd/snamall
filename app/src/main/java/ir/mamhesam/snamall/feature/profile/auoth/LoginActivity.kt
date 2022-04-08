package ir.mamhesam.snamall.feature.profile.auoth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    var binding: ActivityLoginBinding?=null
    val authViewModel: AuthViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.btnLogin.setOnClickListener {
            val phone = binding!!.edtLogin.text.toString().trim()
            authViewModel.login(phone)
        }
        authViewModel.loginLiveData.observe(this){
            Snackbar.make(binding!!.coordinator,it.message,Snackbar.LENGTH_LONG).show()
            finish()
        }

        binding!!.txtIntentRegister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            finish()
        }



    }
}