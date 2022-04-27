package ir.mamhesam.snamall.feature.profile.address

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.gson.JsonObject
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.base.BaseActivity
import ir.mamhesam.snamall.databinding.ActivityAddAddressBinding
import ir.mamhesam.snamall.databinding.ActivityAddressBinding
import ir.mamhesam.snamall.feature.profile.address.viewmodel.AddressViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddAddressActivity : BaseActivity() {
    lateinit var binding: ActivityAddAddressBinding
    val addressViewModel: AddressViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_add_address)
        binding = ActivityAddAddressBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())
        binding.btnAddAddress.setOnClickListener {

            when{
                binding.edtOstan.length() == 0->{
                    binding.edtOstan.error = "استان را وارد کنید"
                }
                binding.edtFamily.length() == 0->{
                    binding.edtFamily.error = "نام خود را وارد کنید"
                }
                binding.edtAdress.length()==0->{
                    binding.edtAdress.error = "لطفا آدرس را وارد نمایید"
                }
                else->{
                    val jsonObject= JsonObject()
                    jsonObject.addProperty("state",binding.edtOstan.text.toString().trim())
                    jsonObject.addProperty("city",binding.edtCity.text.toString().trim())
                    jsonObject.addProperty("address",binding.edtAdress.text.toString().trim())
                    jsonObject.addProperty("unit",binding.edtUnit.text.toString().trim())
                    jsonObject.addProperty("number",binding.edtPelak.text.toString().trim())
                    jsonObject.addProperty("postal_code",binding.edtCode.text.toString().trim())
                    jsonObject.addProperty("name_family",binding.edtFamily.text.toString().trim())
                    jsonObject.addProperty("phone",binding.edtPhone.text.toString().trim())
                    jsonObject.addProperty("lat","232334")
                    jsonObject.addProperty("lang","343434")
                    addressViewModel.addAddress(jsonObject)
                }

            }

        }
        addressViewModel.addAddressLiveData.observe(this){
            Toast.makeText(this, "اطلاعات شما ثبت شد", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}