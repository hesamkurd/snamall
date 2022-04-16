package ir.mamhesam.snamall.feature.home.detailproduct.comment.insertcomment

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.base.BaseActivity
import ir.mamhesam.snamall.databinding.ActivityInsertCommentBinding
import ir.mamhesam.snamall.feature.home.detailproduct.comment.insertcomment.viewmodel.InsertCommentViewModel
import ir.mamhesam.snamall.utils.PRODUCT_ID
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class InsertCommentActivity : BaseActivity() {
    lateinit var binding : ActivityInsertCommentBinding
    val insertCommentViewModel:InsertCommentViewModel by viewModel { parametersOf(intent.getIntExtra(
        PRODUCT_ID,0))  }
    lateinit var positive: String
    lateinit var negative: String
    lateinit var advice: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_insert_comment)
        binding = ActivityInsertCommentBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        val aadvice = ""
        advice = aadvice
        positive = ""
        negative =""
        val productId = intent.getIntExtra(PRODUCT_ID,0)
        binding.txtTitle.text = "ثبت نظر"
        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.imgAddPostive.setOnClickListener {
            if (binding.edtPosotive.length() != 0){
                val sPositive = StringBuilder(binding.edtPosotive.text.toString())
                binding.txtPosptive.append("\n"+sPositive)
                binding.edtPosotive.text.clear()
                sPositive.append("")
                positive = binding.txtPosptive.text.toString().trim().replace("\n",",")
            }

        }
        binding.imgAddNegative.setOnClickListener {
            if (binding.edtNegative.length() != 0){
                val sNegative = StringBuilder(binding.edtNegative.text.toString())
                binding.txtNegative.append("\n"+sNegative)
                binding.edtNegative.text.clear()
                sNegative.append("")
                negative = binding.txtNegative.text.toString().trim().replace("\n",",")

            }

        }
        binding.img1.setOnClickListener {
            advice = "توصیه می کنم"
            binding.img1.setColorFilter(resources.getColor(R.color.green_600))
            binding.img2.setColorFilter(resources.getColor(R.color.grey_900))
            binding.img3.setColorFilter(resources.getColor(R.color.grey_900))
        }
        binding.img2.setOnClickListener {
            advice = "مطمئن نیستم"
            binding.img1.setColorFilter(resources.getColor(R.color.grey_900))
            binding.img2.setColorFilter(resources.getColor(R.color.yellow))
            binding.img3.setColorFilter(resources.getColor(R.color.grey_900))
        }
        binding.img3.setOnClickListener {
            advice = "توصیه نمی کنم"
            binding.img1.setColorFilter(resources.getColor(R.color.grey_900))
            binding.img2.setColorFilter(resources.getColor(R.color.grey_900))
            binding.img3.setColorFilter(resources.getColor(R.color.red))
        }

        binding.btnAddCommnet.setOnClickListener {
            if (advice != "" && binding.edtTitle.text.toString() != "" &&
                binding.edtContent.text.toString() != "" && positive != "" && negative != ""){
                insertCommentViewModel.insertComment(productId,binding.edtContent.text.toString(),binding.edtTitle.text.toString()
                    ,positive,negative,advice)
            }else{
                Snackbar.make(binding.coordinator,"لطفا تمام موارد را کامل کنید",Snackbar.LENGTH_LONG).show()
            }

        }

        insertCommentViewModel.insertCommentLiveData.observe(this){
            Snackbar.make(binding.coordinator,it.message,Snackbar.LENGTH_LONG).show()
        }
    }
}