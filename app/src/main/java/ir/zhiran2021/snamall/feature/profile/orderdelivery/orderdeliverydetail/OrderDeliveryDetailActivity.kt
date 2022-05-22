package ir.zhiran2021.snamall.feature.profile.orderdelivery.orderdeliverydetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityOrderDeliveryDetailBinding
import ir.zhiran2021.snamall.feature.profile.orderdelivery.orderdeliverydetail.adapter.OrderDetailDeliveryAdapter
import ir.zhiran2021.snamall.feature.profile.orderdelivery.orderdeliverydetail.viewmodel.OrderDetailDeliveryViewModel
import ir.zhiran2021.snamall.utils.PriceConverter
import ir.zhiran2021.snamall.utils.REF_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class OrderDeliveryDetailActivity : BaseActivity() {
    lateinit var binding: ActivityOrderDeliveryDetailBinding
    val orderDetailDeliveryViewModel: OrderDetailDeliveryViewModel by viewModel { parametersOf(intent.getStringExtra(
        REF_ID)) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_order_delivery_detail)
        binding = ActivityOrderDeliveryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.txtTitle.text = "جزئیات پرداخت"
        orderDetailDeliveryViewModel.orderDetailDeliveryLiveData.observe(this){
            binding.apply {
                txtRefId.text = it.refId
                txtDate.text = it.dateTime
                txtName.text = it.nameFamily
                txtPhone.text = it.phone
                txtAddress.text = it.address
                txtTotal.text = PriceConverter.priceConvert(it.totalPrice)
                txtType.text = it.payType
                txtShippingCode.text = it.shippingCode
                txtShipping.text = PriceConverter.priceConvert(it.shippingPrice)

            }
            if (it.status == "3"){
                binding.txtSendProduct.visibility = View.VISIBLE
                binding.txtSend.text = "تحویل داده شد"
                binding.progressSend.progress = 3
            }
            val orderDetailDeliveryAdapter: OrderDetailDeliveryAdapter by inject { parametersOf(it.orderDetail) }
            binding.rcOrder.layoutManager = LinearLayoutManager(this)
            binding.rcOrder.adapter = orderDetailDeliveryAdapter

        }
        orderDetailDeliveryViewModel.progressBarLiveData.observe(this){
            setProgressBar(it)
        }

    }
}