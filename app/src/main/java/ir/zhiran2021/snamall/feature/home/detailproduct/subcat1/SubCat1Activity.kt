package ir.zhiran2021.snamall.feature.home.detailproduct.subcat1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivitySubCat1Binding

class SubCat1Activity : BaseActivity() {
    lateinit var binding: ActivitySubCat1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_sub_cat1)
        binding = ActivitySubCat1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.txtTitle.text = intent.getStringExtra("namesubcat1")

    }
}