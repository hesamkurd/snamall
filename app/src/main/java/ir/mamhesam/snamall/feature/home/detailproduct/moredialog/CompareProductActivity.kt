package ir.mamhesam.snamall.feature.home.detailproduct.moredialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.databinding.ActivityCompareProductBinding

class CompareProductActivity : AppCompatActivity() {
    var binding: ActivityCompareProductBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare_product)
        binding = ActivityCompareProductBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding!!.txtTitle.text = "مقایسه محصول"
    }
}