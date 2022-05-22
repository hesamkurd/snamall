package ir.zhiran2021.snamall.feature.profile.orderdelivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityOrderDeliveryBinding
import ir.zhiran2021.snamall.feature.profile.orderdelivery.adapter.OrderDeliveryAdapter
import ir.zhiran2021.snamall.feature.profile.orderdelivery.orderdeliverydetail.OrderDeliveryDetailActivity
import ir.zhiran2021.snamall.feature.profile.orderdelivery.viewmodel.OrderDeliveryViewModel
import ir.zhiran2021.snamall.utils.REF_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class OrderDeliveryActivity : BaseActivity(),OrderDeliveryAdapter.OnClickItemOrderDelivery  {
    lateinit var binding: ActivityOrderDeliveryBinding
    val orderDeliveryViewModel: OrderDeliveryViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_order_delivery)
        binding = ActivityOrderDeliveryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.txtTitle.text = "محصولات تحویل داده شده"

        orderDeliveryViewModel.orderDeliveryLiveData.observe(this){
            val orderDeliveryAdapter: OrderDeliveryAdapter by inject { parametersOf(it) }
            binding.rcOrderDelivery.layoutManager = LinearLayoutManager(this)
            binding.rcOrderDelivery.adapter = orderDeliveryAdapter
            orderDeliveryAdapter.setOnCLickAddress(this)
        }

        orderDeliveryViewModel.progressBarLiveData.observe(this){
            setProgressBar(it)
        }


    }

    override fun onClickOrder(refId: String) {
        startActivity(Intent(this,OrderDeliveryDetailActivity::class.java).apply {
            putExtra(REF_ID,refId)
        })
    }
}