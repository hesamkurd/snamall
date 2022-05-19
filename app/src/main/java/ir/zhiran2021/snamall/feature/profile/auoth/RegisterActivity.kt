package ir.zhiran2021.snamall.feature.profile.auoth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import ir.zhiran2021.snamall.databinding.ActivityRegisterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    val authViewModel: AuthViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtIntentLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        binding.btnCheckUser.setOnClickListener {
            when{
                binding.edtPhoneCheckUser.length() == 0 ->{
                    Snackbar.make(binding.coordinator,"مقادیر لازم را وارد نمایید",Snackbar.LENGTH_LONG).show()
                    binding.edtPhoneCheckUser.error = "شماره تلفن را وارد نمایید"
                }
                binding.edtNameFamily.length() == 0 ->{
                    Snackbar.make(binding.coordinator,"مقادیر لازم را وارد نمایید",Snackbar.LENGTH_LONG).show()
                    binding.edtNameFamily.error = "نام و نام خانوادگی را وارد نمایید"
                }
                else->{
                    val phone = binding.edtPhoneCheckUser.text.toString()
                    authViewModel.checkUser(phone)
                }
            }

        }

        authViewModel.checkUserLiveData.observe(this){
            if (it.message == "1"){
                startActivity(Intent(this,VerifyActivity::class.java).apply {
                    putExtra("name", binding.edtNameFamily.text.toString())
                    putExtra("phone", binding.edtPhoneCheckUser.text.toString())
                })
                finish()
            }else{
                Snackbar.make(binding.coordinator,it.message,Snackbar.LENGTH_LONG).show()
            }
        }
    }
}