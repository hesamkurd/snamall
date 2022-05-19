package ir.zhiran2021.snamall.feature.cart.nextlevel

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.data.ResponseCountCart
import ir.zhiran2021.snamall.databinding.ActivityNextLevelBinding
import ir.zhiran2021.snamall.feature.cart.nextlevel.adapter.CheckOutAdapter
import ir.zhiran2021.snamall.feature.cart.nextlevel.viewmodel.CheckOutListViewModel
import ir.zhiran2021.snamall.feature.profile.address.AddressActivity
import ir.zhiran2021.snamall.feature.profile.auoth.TokenContainer
import ir.zhiran2021.snamall.feature.profile.order.OrderActivity
import ir.zhiran2021.snamall.utils.ADDRESS_ID
import ir.zhiran2021.snamall.utils.DIRECT
import ir.zhiran2021.snamall.utils.PriceConverter
import ir.zhiran2021.snamall.utils.WALLET
import org.greenrobot.eventbus.EventBus
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NextLevelActivity : BaseActivity(),BuyDialog.OnDialogBuy {
    lateinit var binding: ActivityNextLevelBinding
    val checkOutListViewModel: CheckOutListViewModel by viewModel()
    lateinit var buyDialog: BuyDialog
    lateinit var wallet:String
    var payable:Int?=null
    var shippingPrice:Int?=null
    var reciveId:Int?=null
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
        binding.btnBuyProduct.setOnClickListener {
            buyDialog = BuyDialog.newInstance(wallet)
            buyDialog.show(supportFragmentManager,null)
            buyDialog.setOnBuyDialog(this)
        }
        binding.txtTitle.text = "اطلاعات ارسال"
        checkOutListViewModel.checkOutListLiveData.observe(this){
            wallet = it.wallet
            payable = it.payablePrice
            shippingPrice = it.shippingCost
            reciveId = it.addressId
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
            binding.txtName.text = data?.getStringExtra("name")
            binding.txtAddress.text = data?.getStringExtra("address")
            reciveId = data?.getIntExtra(ADDRESS_ID,0)

        }
    }

    override fun onClickBuy(type: String) {
        when(type){
            WALLET->{
                if (wallet.toInt()> payable!!){
                    checkOutListViewModel.insertTransaction(reciveId.toString(),shippingPrice.toString(),payable.toString())
                    checkOutListViewModel.insertTransactionLiveData.observe(this){
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                        buyDialog.dismiss()
                        startActivity(Intent(this,OrderActivity::class.java))
                        finish()
                        val countItem = EventBus.getDefault().getStickyEvent(ResponseCountCart::class.java)
                        countItem?.let {it1->
                            it1.count = 0
                            EventBus.getDefault().postSticky(it1)
                        }
                    }

                }else{
                    Toast.makeText(this, "کیف پول خود را شارژ کنید", Toast.LENGTH_SHORT).show()
                }
            }
            DIRECT->{
                val url = "http://snamall1.mamhesam.ir/v1/api/cart/checkout.php?reciver_id="+reciveId+"&shipping_price="+shippingPrice.toString()+"&payable_price=1000"+"&HTTP_AUTHORIZATION="+TokenContainer.token

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
                finish()
                val countItem = EventBus.getDefault().getStickyEvent(ResponseCountCart::class.java)
                countItem?.let {it1->
                    it1.count = 0
                    EventBus.getDefault().postSticky(it1)
                }
            }
        }
    }
}