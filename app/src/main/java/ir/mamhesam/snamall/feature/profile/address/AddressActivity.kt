package ir.mamhesam.snamall.feature.profile.address

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.base.BaseActivity
import ir.mamhesam.snamall.data.ResponseShowAddress
import ir.mamhesam.snamall.databinding.ActivityAddressBinding
import ir.mamhesam.snamall.feature.profile.address.adapter.ShowAddressAdapter
import ir.mamhesam.snamall.feature.profile.address.viewmodel.AddressViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AddressActivity : BaseActivity(),ShowAddressAdapter.OnClickItemAddress {
    lateinit var binding: ActivityAddressBinding
    val addressViewModel: AddressViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_address)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.txtTitle.text = "آدرس ها"
        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.fabAddress.setOnClickListener {
            startActivity(Intent(this,AddAddressActivity::class.java))
        }

        addressViewModel.addressLiveData.observe(this){
            val addressAdapter: ShowAddressAdapter by inject { parametersOf(it) }
            binding.rcShowAddress.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
            binding.rcShowAddress.adapter = addressAdapter
            addressAdapter.setOnCLickAddress(this)
        }
        addressViewModel.progressBarLiveData.observe(this){
            setProgressBar(it)
        }
    }

    override fun onClickAddress(address: ResponseShowAddress) {

        setResult(1001, Intent().
        putExtra("name",address.nameFamily).
        putExtra("address",address.address))
        finish()
    }

    override fun onStart() {
        super.onStart()
        addressViewModel.getAddress()
    }
}