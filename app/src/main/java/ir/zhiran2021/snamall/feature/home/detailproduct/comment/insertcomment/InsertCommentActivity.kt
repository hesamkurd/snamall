package ir.zhiran2021.snamall.feature.home.detailproduct.comment.insertcomment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.data.ScoreItem
import ir.zhiran2021.snamall.databinding.ActivityInsertCommentBinding
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.insertcomment.adapter.ShowScoreAdapter
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.insertcomment.viewmodel.InsertCommentViewModel
import ir.zhiran2021.snamall.utils.PRODUCT_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class InsertCommentActivity : BaseActivity(),ShowScoreAdapter.OnChangeSliderScore {
    lateinit var binding : ActivityInsertCommentBinding
    val insertCommentViewModel:InsertCommentViewModel by viewModel { parametersOf(intent.getIntExtra(
        PRODUCT_ID,0))  }
    lateinit var positive: String
    lateinit var negative: String
    lateinit var advice: String
    var scoreArray= ArrayList<ScoreItem>()
    lateinit var score:String
    var checkStatus:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_insert_comment)
        binding = ActivityInsertCommentBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        val aadvice = ""
        advice = aadvice
        positive = ""
        negative =""
        score = ""
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
            if (checkStatus){
                if (advice != "" && binding.edtTitle.text.toString() != "" &&
                    binding.edtContent.text.toString() != "" && positive != "" && negative != "" && score !=""){
                    insertCommentViewModel.insertCommentPro(productId,binding.edtContent.text.toString(),binding.edtTitle.text.toString()
                        ,positive,negative,advice,score!!)
                }else{
                    Snackbar.make(binding.coordinator,"لطفا تمام موارد را کامل کنید",Snackbar.LENGTH_LONG).show()
                }
            }else{
                if (advice != "" && binding.edtTitle.text.toString() != "" &&
                    binding.edtContent.text.toString() != "" && positive != "" && negative != ""){
                    insertCommentViewModel.insertComment(productId,binding.edtContent.text.toString(),binding.edtTitle.text.toString()
                        ,positive,negative,advice)
                }else{
                    Snackbar.make(binding.coordinator,"لطفا تمام موارد را کامل کنید",Snackbar.LENGTH_LONG).show()
                }
            }


        }

        insertCommentViewModel.insertCommentLiveData.observe(this){
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            finish()
        }
        insertCommentViewModel.progressBarLiveData.observe(this){
            setProgressBar(it)
        }
        insertCommentViewModel.showScoreLiveData.observe(this){
            if (it.status == "1"){
                checkStatus = true
                binding.rcScore.visibility = View.VISIBLE
                val showScoreAdapter: ShowScoreAdapter by inject { parametersOf(it.score) }
                binding.rcScore.layoutManager = LinearLayoutManager(this)
                binding.rcScore.adapter = showScoreAdapter
                showScoreAdapter.setOnSliderChange(this)
            }else{
                checkStatus = false
                binding.rcScore.visibility = View.GONE
            }

        }
    }

    override fun onChangeItemScore(value: Float, item: ScoreItem) {
        scoreArray.remove(item)
        item.value = value.toInt()
        scoreArray.add(item)
        score = scoreArray.toString().replace("ScoreItem","").trim()
    }
}