package ir.zhiran2021.snamall.feature.home.detailproduct.comment

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.databinding.ActivityShowCommentBinding
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.adapter.ShowCommentAdapter
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.adapter.ShowRatingCommentAdapter
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.insertcomment.InsertCommentActivity
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.viewmodel.CommentViewModel
import ir.zhiran2021.snamall.feature.profile.auoth.AuthViewModel
import ir.zhiran2021.snamall.feature.profile.auoth.LoginActivity
import ir.zhiran2021.snamall.utils.PRODUCT_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ShowCommentActivity : BaseActivity() {
    lateinit var binding: ActivityShowCommentBinding
    val commentViewModel: CommentViewModel by viewModel { parametersOf(intent.getIntExtra("id",0)) }
    val authViewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_show_comment)
        binding = ActivityShowCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.fabIntentInsertCommnet.setOnClickListener {

            if (authViewModel.checkLoginLiveData.value == true){
                startActivity(Intent(this,InsertCommentActivity::class.java).apply {
                    putExtra(PRODUCT_ID,intent.getIntExtra("id",0))
                })
            }else{
                startActivity(Intent(this, LoginActivity::class.java))

            }

        }

        binding.txtTitle.text = "نظرات کاربران"
        commentViewModel.showRatingCommentLiveData.observe(this){
            val showRatingCommentAdapter: ShowRatingCommentAdapter by inject { parametersOf(it) }
            binding.rcShowRating.layoutManager = LinearLayoutManager(this)
            binding.rcShowRating.adapter = showRatingCommentAdapter
        }
        commentViewModel.showCommentLiveData.observe(this){
            val showCommentAdapter: ShowCommentAdapter by inject { parametersOf(it) }
            binding.rcShowComment.layoutManager = LinearLayoutManager(this)
            binding.rcShowComment.adapter = showCommentAdapter
        }

    }
    override fun onResume() {
        super.onResume()
        authViewModel.checkLogin()
    }
}