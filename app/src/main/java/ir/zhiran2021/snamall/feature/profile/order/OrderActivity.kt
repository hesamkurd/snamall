package ir.zhiran2021.snamall.feature.profile.order

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityOrderBinding
import ir.zhiran2021.snamall.feature.profile.order.adapter.OrderAdapter
import ir.zhiran2021.snamall.feature.profile.order.orderdetail.OrderDetailActivity
import ir.zhiran2021.snamall.feature.profile.order.viewmodel.OrderHistoryViewModel
import ir.zhiran2021.snamall.utils.REF_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class OrderActivity : BaseActivity() , OrderAdapter.OnClickItemOrder{
    lateinit var binding: ActivityOrderBinding
    val orderHistoryViewModel: OrderHistoryViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_order)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.txtTitle.text = "لیست تراکنش ها"

        orderHistoryViewModel.orderHistoryLiveData.observe(this){
            val orderAdapter: OrderAdapter by inject { parametersOf(it) }
            binding.rcOrder.layoutManager = LinearLayoutManager(this)
            binding.rcOrder.adapter = orderAdapter
            orderAdapter.setOnCLickAddress(this)
        }

        orderHistoryViewModel.progressBarLiveData.observe(this){
            setProgressBar(it)
        }
    }

    override fun onClickOrder(refId: String) {
        startActivity(Intent(this,OrderDetailActivity::class.java).apply {
            putExtra(REF_ID,refId)
        })
    }
}