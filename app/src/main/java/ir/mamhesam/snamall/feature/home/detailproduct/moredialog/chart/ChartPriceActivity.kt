package ir.mamhesam.snamall.feature.home.detailproduct.moredialog.chart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.databinding.ActivityChartPriceBinding

class ChartPriceActivity : AppCompatActivity() {
    var binding: ActivityChartPriceBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_chart_price)
        binding = ActivityChartPriceBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding!!.txtTitle.text = "نمودار قیمت"
    }
}