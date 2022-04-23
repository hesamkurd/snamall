package ir.mamhesam.snamall.feature.home.detailproduct

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils
import com.google.android.material.color.MaterialColors
import com.google.android.material.snackbar.Snackbar
import ir.mamhesam.snamall.MainViewModel
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.base.BaseActivity
import ir.mamhesam.snamall.data.ResponseCountCart
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
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DetailActivity : BaseActivity(),
    MoreDialogBottomSheet.OnClickMoreDialog,
    ColorAdapter.OnClickColorItem, SizeAdapter.OnClickSizeItem {

    var binding: ActivityDetailBinding? = null

    val detailProductViewModel: DetailProductViewModel by viewModel {
        parametersOf(
            intent.getIntExtra(
                "id",
                0
            )
        )
    }
    val authViewModel: AuthViewModel by viewModel()
    val mainViewModel: MainViewModel by viewModel()

    var idProduct: Int? = null
    var idColor: Int = 0
    var idSize: Int = 0
    var checkColor: Boolean = true
    var checkSize: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        EventBus.getDefault().register(this)

        binding!!.imgMore.setOnClickListener {
            val moreDialog = MoreDialogBottomSheet()
            moreDialog.show(supportFragmentManager, null)
            moreDialog.setOnClicKDialog(this)
        }
        binding!!.technicalProperty.setOnClickListener {
            startActivity(Intent(this, TechnicalPropertyActivity::class.java).apply {
                putExtra("id", idProduct)
            })
        }
        binding!!.lnrDescription.setOnClickListener {
            startActivity(Intent(this, DescriptionActivity::class.java).apply {
                putExtra("id", idProduct)
            })
        }
        binding!!.imgFavorite.setOnClickListener {
            if (authViewModel.checkLoginLiveData.value == true) {
                authViewModel.addToFavorite(idProduct!!)

            } else {
                startActivity(Intent(this, LoginActivity::class.java))

            }
        }

        authViewModel.addToFavoriteLiveData.observe(this) {
            if (it.status == "true") {
                binding!!.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                Snackbar.make(binding!!.coordinator, it.message, Snackbar.LENGTH_LONG).show()
            } else {
                binding!!.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                Snackbar.make(binding!!.coordinator, it.message, Snackbar.LENGTH_LONG).show()

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
            if (it.offPrice == "0") {
                binding!!.txtPrice.text = PriceConverter.priceConvert(it.price)
                binding!!.txtPrice.textSize = 12f
                binding!!.txtPrice.setTextColor(ContextCompat.getColor(this, R.color.grey_900))
                binding!!.txtFreePrice.visibility = View.GONE
                binding!!.txtFree.visibility = View.GONE
            } else {
                binding!!.txtPrice.text = PriceConverter.priceConvert(it.price)
                binding!!.txtPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                binding!!.txtFreePrice.text = PriceConverter.priceConvert(it.offPrice)
                binding!!.txtFree.text = FreePercent.offPercent(it.offPercent)
            }
            if (it.status == "true") {
                binding!!.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                binding!!.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
            ///
            if (it.productColors.isNullOrEmpty()) {
                binding!!.rcColors.visibility = View.GONE
                binding!!.colorTitle.visibility = View.GONE
                checkColor = false
            } else {
                val colorAdapter: ColorAdapter by inject { parametersOf(it.productColors) }
                binding!!.rcColors.layoutManager =
                    LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
                binding!!.rcColors.adapter = colorAdapter
                colorAdapter.setOnClickColor(this)


            }
            if (it.productSizes.isNullOrEmpty()) {
                binding!!.rcSize.visibility = View.GONE
                binding!!.sizeTitel.visibility = View.GONE
                checkSize = false
            } else {

                val sizeAdapter: SizeAdapter by inject { parametersOf(it.productSizes) }

                binding!!.rcSize.layoutManager =
                    LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
                binding!!.rcSize.adapter = sizeAdapter
                sizeAdapter.setOnClickSize(this)

            }

            val similarAdapter: SimilarAdapter by inject { parametersOf(it.similarProduct) }
            if (it.similarProduct.isNullOrEmpty()) {
                binding!!.rcSimilar.visibility = View.GONE
            } else {
                binding!!.rcSimilar.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                binding!!.rcSimilar.adapter = similarAdapter
                val itemDecoration: RecyclerView.ItemDecoration =
                    DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
                binding!!.rcSimilar.addItemDecoration(itemDecoration)

            }

            ///
            val propreAdapter: PropertiesAdapter by inject { parametersOf(it.sproperties) }
            if (it.sproperties.isNullOrEmpty()) {
                binding!!.rcProperties.visibility = View.GONE
            } else {
                binding!!.rcProperties.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding!!.rcProperties.adapter = propreAdapter
            }

            ///
            val commentAdapter: CommentAdapter by inject { parametersOf(it.comments) }
            if (it.comments.isNullOrEmpty()) {
                binding!!.rcComments.visibility = View.GONE
            } else {
                binding!!.rcComments.layoutManager =
                    LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
                binding!!.rcComments.adapter = commentAdapter
            }
            binding!!.txtCountComment.setOnClickListener {
                startActivity(Intent(this, ShowCommentActivity::class.java).apply {
                    putExtra("id", idProduct)
                })
            }


        }
        detailProductViewModel.progressBarLiveData.observe(this) {
            setProgressBar(it)
        }

        binding!!.btnAddToCart.setOnClickListener {
            if (checkColor && !checkSize) {
                if (idColor != 0)
                    authViewModel.addToCart(idProduct!!, idColor, 0)
                else
                    Snackbar.make(
                        binding!!.coordinator,
                        "لطفا رنگ را انتخاب کنید",
                        Snackbar.LENGTH_SHORT
                    ).show()
            } else if (checkSize && !checkColor) {
                if (idSize != 0)
                    authViewModel.addToCart(idProduct!!, 0, idSize)
                else
                    Snackbar.make(
                        binding!!.coordinator,
                        "لطفا سایز را انتخاب کنید",
                        Snackbar.LENGTH_SHORT
                    ).show()

            } else if (!checkColor && !checkSize) {

                authViewModel.addToCart(idProduct!!, idColor, idSize)
            } else {
                if (idColor == 0 || idSize == 0) {
                    Snackbar.make(
                        binding!!.coordinator,
                        "لطفا سایز و رنگ را انتخاب کنید",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    authViewModel.addToCart(idProduct!!, idColor, idSize)
                }

            }
        }
        authViewModel.addToCartLiveData.observe(this) {
            Snackbar.make(binding!!.coordinator, it.message, Snackbar.LENGTH_SHORT).show()

            val countItem = EventBus.getDefault().getStickyEvent(ResponseCountCart::class.java)
            countItem?.let {it1->
                it1.count ++
                EventBus.getDefault().postSticky(it1)
            }

        }


    }

    @SuppressLint("UnsafeOptInUsageError")
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun countCart(countItem: ResponseCountCart) {
        val badge = BadgeDrawable.create(this)
        val image = findViewById<ImageView>(R.id.img_basket)
        badge.backgroundColor =
            MaterialColors.getColor(binding!!.rltToolbar, com.mukesh.R.attr.colorPrimary)
        badge.badgeGravity = BadgeDrawable.TOP_START
        badge.verticalOffset = 15
        badge.horizontalOffset = 15
        badge.number = countItem.count
        badge.isVisible = countItem.count > 0
        BadgeUtils.attachBadgeDrawable(badge, image, findViewById(R.id.rlt_toolbar))

    }

    override fun onClickMore(type: String) {
        when (type) {
            CHART -> {
                startActivity(Intent(this, ChartPriceActivity::class.java))
            }
            COMPARE -> {
                startActivity(Intent(this, CompareProductActivity::class.java))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        authViewModel.checkLogin()
        mainViewModel.getCount()

    }

    override fun onStart() {
        super.onStart()
        mainViewModel.getCount()

    }
    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun onClickColorId(colorId: Int) {
        idColor = colorId
    }

    override fun onClickSizeId(sizeId: Int) {
        idSize = sizeId
    }
}