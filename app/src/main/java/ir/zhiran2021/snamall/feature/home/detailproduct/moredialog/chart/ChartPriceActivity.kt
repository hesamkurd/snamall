package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.chart

import android.graphics.Typeface
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityChartPriceBinding
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.chart.viewmodel.ChartViewModel
import ir.zhiran2021.snamall.utils.PRODUCT_ID
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

val dates = mutableListOf<String>()


class ChartPriceActivity : BaseActivity() {
    var binding: ActivityChartPriceBinding?=null
    val chartViewModel: ChartViewModel by viewModel { parametersOf(intent.getIntExtra(PRODUCT_ID,0)) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_chart_price)
        binding = ActivityChartPriceBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding!!.txtTitle.text = "نمودار قیمت"

        val typeFace : Typeface = Typeface.createFromAsset(assets, "vazir_medium.ttf")
        val legend : Legend = binding!!.lineChart.legend
        legend.typeface = typeFace
        val leftAXis : YAxis = binding!!.lineChart.axisLeft
        leftAXis.labelCount = 7
        leftAXis.typeface = typeFace
        binding!!.lineChart.axisRight.isEnabled = false
        binding!!.lineChart.description.isEnabled = false

        val topAXis = binding!!.lineChart.xAxis
        topAXis.isEnabled = true
        topAXis.typeface = typeFace

        chartViewModel.progressBarLiveData.observe(this){
            setProgressBar(it)
        }

        chartViewModel.chartLiveData.observe(this){

            if (it.isNotEmpty()){
                val values: MutableList<Entry> = ArrayList()
                for (i in it.indices){
                    values.add(Entry(i.toFloat(),it[i].price.toFloat()))
                    dates.add(it[i].date)
                }

                val lineDataSet = LineDataSet(values,"نمودار قیمت").apply {

                    valueTypeface = typeFace
                    color = ContextCompat.getColor(this@ChartPriceActivity, R.color.green_600)
                    lineWidth = 2f
                    valueTextSize = 10f
                    valueTextColor = ContextCompat.getColor(this@ChartPriceActivity, R.color.red)
                    setCircleColor(ContextCompat.getColor(this@ChartPriceActivity,R.color.yellow))
                    setDrawFilled(true)
                    fillDrawable = ContextCompat.getDrawable(this@ChartPriceActivity,R.drawable.shape_chart)

                }

                val iLineDataSet : MutableList<ILineDataSet> = ArrayList()
                iLineDataSet.add(lineDataSet)
                val lineData = LineData(iLineDataSet)
                binding!!.lineChart.data = lineData
                val xAxis = binding!!.lineChart.xAxis
                xAxis.labelCount = 6
                xAxis.valueFormatter = MyAXis()
                binding!!.lineChart.animateX(1000)
                binding!!.lineChart.invalidate()

            }
        }
    }
}
class MyAXis : ValueFormatter(){

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return dates.getOrNull(value.toInt()) ?:value.toString()
    }
}