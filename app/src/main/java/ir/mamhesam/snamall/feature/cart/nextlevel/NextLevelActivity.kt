package ir.mamhesam.snamall.feature.cart.nextlevel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.base.BaseActivity
import ir.mamhesam.snamall.databinding.ActivityNextLevelBinding
import ir.mamhesam.snamall.feature.cart.nextlevel.adapter.CheckOutAdapter
import ir.mamhesam.snamall.feature.cart.nextlevel.viewmodel.CheckOutListViewModel
import ir.mamhesam.snamall.feature.profile.address.AddressActivity
import ir.mamhesam.snamall.utils.PriceConverter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NextLevelActivity : BaseActivity() {
    lateinit var binding: ActivityNextLevelBinding
    val checkOutListViewModel: CheckOutListViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_next_level)
        binding = ActivityNextLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkOutListViewModel.progressBarLiveData.observe(this){
            setProgressBar(it)
        }
        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.txtTitle.text = "اطلاعات ارسال"
        checkOutListViewModel.checkOutListLiveData.observe(this){
            binding.apply {
                txtDelivry.text = it.deliveryTime
                txtPrice.text = PriceConverter.priceConvert(it.totalPrice.toString())
                txtOffprice.text = PriceConverter.priceConvert(it.totalOffPrice.toString())
                txtShipping.text = PriceConverter.priceConvert(it.shippingCost.toString())
                txtPriceOff.text = PriceConverter.priceConvert(it.totalPriceOff.toString())
                txtPayblale.text = PriceConverter.priceConvert(it.payablePrice.toString())
                txtPriceProduct.text = PriceConverter.priceConvert(it.payablePrice.toString())
                txtAddress.text = it.address
                txtName.text = it.nameFamily
            }
            val checkOutAdapter: CheckOutAdapter by inject { parametersOf(it.productItemDeliveries) }
            binding.rcCheckoutProduct.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
            binding.rcCheckoutProduct.adapter = checkOutAdapter
        }
        binding.txtIntentAddress.setOnClickListener {
            val intent =Intent(this,AddressActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

        if (it.resultCode==1001){
            val data: Intent? = it.data
            binding.txtName.text = data!!.getStringExtra("name")
            binding.txtAddress.text = data.getStringExtra("address")
        }
    }
}