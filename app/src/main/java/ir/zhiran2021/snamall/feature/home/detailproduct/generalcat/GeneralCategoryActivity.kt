package ir.zhiran2021.snamall.feature.home.detailproduct.generalcat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.BaseAdapter
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityGeneralCategoryBinding

class GeneralCategoryActivity : BaseActivity() {
    lateinit var binding : ActivityGeneralCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_general_category)
        binding= ActivityGeneralCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtTitle.text = intent.getStringExtra("namecat")
    }
}