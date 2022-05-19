package ir.zhiran2021.snamall.feature.profile.order.orderdetail

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityOrderDetailBinding
import ir.zhiran2021.snamall.feature.profile.order.orderdetail.adapter.OrderDetailAdapter
import ir.zhiran2021.snamall.feature.profile.order.orderdetail.viewmodel.OrderDetailViewModel
import ir.zhiran2021.snamall.utils.PriceConverter
import ir.zhiran2021.snamall.utils.REF_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class OrderDetailActivity : BaseActivity() {
    lateinit var binding: ActivityOrderDetailBinding
    val orderDetailViewModel: OrderDetailViewModel by viewModel { parametersOf(intent.getStringExtra(
        REF_ID)) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_order_detail)
        binding = ActivityOrderDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtTitle.text = "جزئیات تراکنش"
        binding.imgBack.setOnClickListener {
            finish()
        }

        orderDetailViewModel.orderDetailLiveData.observe(this){
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
            when (it.status) {
                "1" -> {
                    binding.txtSendProduct.visibility = View.GONE
                    binding.txtSend.text = "در حال آماده سازی"
                    binding.progressSend.progress = 1
                }
                "2" -> {
                    binding.txtSendProduct.visibility = View.VISIBLE
                    binding.txtSend.text = "تحویل به پست"
                    binding.progressSend.progress = 2
                    binding.txtShippingCode.text = it.shippingCode
                }
                else -> {
                    binding.txtSendProduct.visibility = View.VISIBLE
                    binding.txtSend.text = ""
                    binding.progressSend.progress = 3
                }
            }

            val orderDetailAdapter: OrderDetailAdapter by inject { parametersOf(it.orderDetail) }
            binding.rcOrder.layoutManager = LinearLayoutManager(this)
            binding.rcOrder.adapter = orderDetailAdapter

        }
        orderDetailViewModel.progressBarLiveData.observe(this){
            setProgressBar(it)
        }
    }
}