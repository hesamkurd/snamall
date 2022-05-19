package ir.zhiran2021.snamall.feature.profile.auoth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import ir.zhiran2021.snamall.data.ResponseLogin
import ir.zhiran2021.snamall.databinding.ActivityLoginBinding
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

        binding!!.btnLogin.setOnClickListener {
            val phone = binding!!.edtLogin.text.toString().trim()
            authViewModel.login(phone)

        }
        authViewModel.loginLiveData.observe(this){
            if (it.message == "1"){
                Snackbar.make(binding!!.coordinator,it.message,Snackbar.LENGTH_LONG).show()
                Toast.makeText(this, "با موفقیت وارد شدید", Toast.LENGTH_LONG).show()
                finish()
            }else{
                Snackbar.make(binding!!.coordinator,it.message,Snackbar.LENGTH_LONG).show()
                startActivity(Intent(this,RegisterActivity::class.java))
                Toast.makeText(this, "لطفا ابتدا وارد شوید", Toast.LENGTH_LONG).show()

                finish()



            }


        }

        binding!!.txtIntentRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }


    }
}