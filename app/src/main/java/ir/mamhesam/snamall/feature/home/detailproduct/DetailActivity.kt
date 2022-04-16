package ir.mamhesam.snamall.feature.home.detailproduct

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.base.BaseActivity
import ir.mamhesam.snamall.databinding.ActivityDetailBinding
import ir.mamhesam.snamall.feature.home.detailproduct.adapter.*
import ir.mamhesam.snamall.feature.home.detailproduct.comment.ShowCommentActivity
import ir.mamhesam.snamall.feature.home.detailproduct.moredialog.ChartPriceActivity
import ir.mamhesam.snamall.feature.home.detailproduct.moredialog.CompareProductActivity
import ir.mamhesam.snamall.feature.home.detailproduct.moredialog.MoreDialogBottomSheet
import ir.mamhesam.snamall.feature.home.detailproduct.property.DescriptionActivity
import ir.mamhesam.snamall.feature.home.detailproduct.property.TechnicalPropertyActivity
import ir.mamhesam.snamall.feature.home.detailproduct.viewmodel.DetailProductViewModel
import ir.mamhesam.snamall.feature.profile.auoth.AuthViewModel
import ir.mamhesam.snamall.feature.profile.auoth.LoginActivity
import ir.mamhesam.snamall.utils.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DetailActivity : BaseActivity(),MoreDialogBottomSheet.OnClickMoreDialog {

    var binding: ActivityDetailBinding?=null
    var idProduct: Int?=null
    val detailProductViewModel: DetailProductViewModel by viewModel {
        parametersOf(
            intent.getIntExtra(
                "id",
                0
            )
        )
    }
    val authViewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding!!.imgMore.setOnClickListener {
            val moreDialog = MoreDialogBottomSheet()
            moreDialog.show(supportFragmentManager,null)
            moreDialog.setOnClicKDialog(this)
        }
        binding!!.technicalProperty.setOnClickListener {
            startActivity(Intent(this,TechnicalPropertyActivity::class.java).apply {
                putExtra("id", idProduct)
            })
        }
        binding!!.lnrDescription.setOnClickListener {
            startActivity(Intent(this,DescriptionActivity::class.java).apply {
                putExtra("id",idProduct)
            })
        }
        binding!!.imgFavorite.setOnClickListener {
            if (authViewModel.checkLoginLiveData.value == true){
                authViewModel.addToFavorite(idProduct!!)

            }else{
                startActivity(Intent(this,LoginActivity::class.java))

            }
        }

        authViewModel.addToFavoriteLiveData.observe(this){
            if (it.status == "true"){
                binding!!.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                Snackbar.make(binding!!.coordinator,it.message,Snackbar.LENGTH_LONG).show()
            }else{
                binding!!.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                Snackbar.make(binding!!.coordinator,it.message,Snackbar.LENGTH_LONG).show()

            }
        }

        detailProductViewModel.detailProductLiveData.observe(this) {
            idProduct = it.id
            val galleryAdapter: GalleryAdapter by inject { parametersOf(it.images) }
            binding?.apply {
                galleryViewpager.adapter = galleryAdapter
                dotsIndicator.setViewPager2(galleryViewpager)
            }
            val animalNames = ArrayList<String>()
            animalNames.add(it.catName)
            animalNames.add(it.subcat1Name)
            animalNames.add(it.subcat2Name)
            binding?.rcCategor?.layoutManager =
                LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            val catAdapter = CatAdapter(animalNames)
            binding?.rcCategor?.adapter = catAdapter

            binding?.apply {
                txtName.text = it.name
                txtBrandName.text = it.brandName
                txtSubBrandName.text = it.subBrandName
                productRating.rating = it.score
                txtSeller.text = it.sellerName
                txtWarranty.text = it.garantyDescription
                txtNumberProduct.text = it.number + " عدد موجود در انبار "
                txtCountComment.text = it.commentCount

            }
            if (it.offPrice == "0"){
                binding!!.txtPrice.text = PriceConverter.priceConvert(it.price)
                binding!!.txtPrice.textSize = 12f
                binding!!.txtPrice.setTextColor(ContextCompat.getColor(this,R.color.grey_900))
                binding!!.txtFreePrice.visibility = View.GONE
                binding!!.txtFree.visibility = View.GONE
            }else{
                binding!!.txtPrice.text = PriceConverter.priceConvert(it.price)
                binding!!.txtPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                binding!!.txtFreePrice.text = PriceConverter.priceConvert(it.offPrice)
                binding!!.txtFree.text = FreePercent.offPercent(it.offPercent)
            }
            if (it.status == "true"){
                binding!!.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            }else{
                binding!!.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
            ///
            val colorAdapter: ColorAdapter by inject { parametersOf(it.productColors) }
            if (it.productColors.isNullOrEmpty()){
                binding?.rcColors?.visibility = View.GONE
                binding!!.colorTitle.visibility = View.GONE
            }else{
                binding?.rcColors?.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                binding?.rcColors?.adapter = colorAdapter
            }

            ///
            val sizeAdapter: SizeAdapter by inject { parametersOf(it.productSizes) }
            if (it.productSizes.isNullOrEmpty()){
                binding!!.rcSimilar.visibility = View.GONE
                binding!!.sizeTitel.visibility = View.GONE
            }else{
                binding!!.rcSize.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                binding!!.rcSize.adapter = sizeAdapter
            }

            ///
            val similarAdapter: SimilarAdapter by inject { parametersOf(it.similarProduct) }
            if (it.similarProduct.isNullOrEmpty()){
                binding!!.rcSimilar.visibility = View.GONE
            }else{
                binding!!.rcSimilar.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                binding!!.rcSimilar.adapter = similarAdapter
                val itemDecoration: RecyclerView.ItemDecoration =
                    DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
                binding!!.rcSimilar.addItemDecoration(itemDecoration)

            }

            ///
            val propreAdapter: PropertiesAdapter by inject { parametersOf(it.sproperties) }
            if (it.sproperties.isNullOrEmpty()){
                binding!!.rcProperties.visibility = View.GONE
            }else{
                binding!!.rcProperties.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding!!.rcProperties.adapter = propreAdapter
            }

            ///
            val commentAdapter: CommentAdapter by inject { parametersOf(it.comments) }
            if (it.comments.isNullOrEmpty()){
                binding!!.rcComments.visibility = View.GONE
            }else{
                binding!!.rcComments.layoutManager =
                    LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
                binding!!.rcComments.adapter = commentAdapter
            }
            binding!!.txtCountComment.setOnClickListener {
                startActivity(Intent(this,ShowCommentActivity::class.java).apply {
                    putExtra("id", idProduct)
                })
            }


        }
        detailProductViewModel.progressBarLiveData.observe(this) {
            setProgressBar(it)
        }


    }

    override fun onClickMore(type: String) {
        when(type) {
            CHART -> {
                startActivity(Intent(this,ChartPriceActivity::class.java))
            }
            COMPARE ->{
                startActivity(Intent(this,CompareProductActivity::class.java))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        authViewModel.checkLogin()
    }
}