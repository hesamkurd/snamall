package ir.zhiran2021.snamall.feature.home.detailproduct.subcat2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivitySubCat2Binding

class SubCat2Activity : BaseActivity() {
    lateinit var binding: ActivitySubCat2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_sub_cat2)
        binding = ActivitySubCat2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.txtTitle.text = intent.getStringExtra("namesubcat2")

    }
}