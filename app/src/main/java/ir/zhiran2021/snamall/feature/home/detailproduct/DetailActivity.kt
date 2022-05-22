package ir.zhiran2021.snamall.feature.home.detailproduct

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.color.MaterialColors
import com.google.android.material.snackbar.Snackbar
import ir.zhiran2021.snamall.MainViewModel
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseActivity
import ir.zhiran2021.snamall.data.ConnectionLiveData
import ir.zhiran2021.snamall.data.ResponsCategory
import ir.zhiran2021.snamall.data.ResponseCountCart
import ir.zhiran2021.snamall.databinding.ActivityDetailBinding
import ir.zhiran2021.snamall.feature.cart.CartFragment
import ir.zhiran2021.snamall.feature.category.brandproduct.BrandActivity
import ir.zhiran2021.snamall.feature.category.subcat2.SubCat2Activity
import ir.zhiran2021.snamall.feature.home.detailproduct.adapter.*
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.ShowCommentActivity
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.insertcomment.InsertCommentActivity
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.MoreDialogBottomSheet
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.chart.ChartPriceActivity
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.CompareCategoryActivity
import ir.zhiran2021.snamall.feature.home.detailproduct.property.DescriptionActivity
import ir.zhiran2021.snamall.feature.home.detailproduct.property.TechnicalPropertyActivity
import ir.zhiran2021.snamall.feature.home.detailproduct.subcat1.SubCat1Activity
import ir.zhiran2021.snamall.feature.home.detailproduct.viewmodel.DetailProductViewModel
import ir.zhiran2021.snamall.feature.home.subcatlevel1.SubCatLevel1Fragment
import ir.zhiran2021.snamall.feature.profile.auoth.AuthViewModel
import ir.zhiran2021.snamall.feature.profile.auoth.LoginActivity
import ir.zhiran2021.snamall.utils.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

const val cat_id = "cat_id"
const val subcat_id1 = "subcat_id1"
const val subcat_id2 = "subcat_id2"

class DetailActivity : BaseActivity(),
    MoreDialogBottomSheet.OnClickMoreDialog,
    ColorAdapter.OnClickColorItem,
    SizeAdapter.OnClickSizeItem,
    CatAdapter.OnClickCategory,
    SimilarAdapter.OnClickSimilarItem, CommentAdapter.OnClickCommentItem {

    var binding: ActivityDetailBinding? = null


    val detailProductViewModel: DetailProductViewModel by viewModel {
        parametersOf(
            intent.getIntExtra(
                "id",
                0
            )
        )
    }
    lateinit var cld: ConnectionLiveData
    val authViewModel: AuthViewModel by viewModel()
    val mainViewModel: MainViewModel by viewModel()

    var idProduct: Int? = null
    var nameProduct:String? = null
    var idcat: Int? = null
    var idsubcat1: Int? = null
    var idsubcat2: Int? = null
    var namecat: String? = null
    var namesubcat1: String? = null
    var namesubcat2: String? = null
    var idColor: Int = 0
    var idSize: Int = 0
    var checkColor: Boolean = true
    var checkSize: Boolean = true
    lateinit var cartFragment: CartFragment
    var itemCat: List<ResponsCategory>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        checkNetwork()
        EventBus.getDefault().register(this)


        binding!!.imgMore.setOnClickListener {
            val moreDialog = MoreDialogBottomSheet()
            moreDialog.show(supportFragmentManager, null)
            moreDialog.setOnClicKDialog(this)
        }
//        val navController = DetailActivity.findNavController(this,R.id.nav_host_fragment)

        binding!!.imgBasket.setOnClickListener {
            val mFragment: Fragment
            mFragment = CartFragment()
            val fragmentManager: FragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.detail_framelayout, mFragment).commit()

        }
        binding!!.imgDismis.setOnClickListener {
            finish()
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

        binding!!.lnrInsertComment.setOnClickListener {
            if (authViewModel.checkLoginLiveData.value == true){
                startActivity(Intent(this, InsertCommentActivity::class.java).apply {
                    putExtra(PRODUCT_ID,idProduct)
                })
            }else{
                startActivity(Intent(this, LoginActivity::class.java))

            }

        }

        binding!!.txtBrandName.setOnClickListener {
            startActivity(Intent(this,BrandActivity::class.java).apply {
                putExtra(PRODUCT_ID,nameProduct)
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
                Snackbar.make(binding!!.coordinatorDetail, it.message, Snackbar.LENGTH_LONG).show()
            } else {
                binding!!.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                Snackbar.make(binding!!.coordinatorDetail, it.message, Snackbar.LENGTH_LONG).show()

            }
        }

        detailProductViewModel.detailProductLiveData.observe(this) {
            idProduct = it.id
            nameProduct = it.brandName
            val galleryAdapter: GalleryAdapter by inject { parametersOf(it.imagesOrder) }

            binding?.apply {
                galleryViewpager.adapter = galleryAdapter
                dotsIndicator.setViewPager2(galleryViewpager)
            }


            val categories = ArrayList<ResponsCategory>()
            categories.add(ResponsCategory(it.catName, it.catId.toInt()))
            categories.add(ResponsCategory(it.subcat1Name, it.subcat1Id.toInt()))
            categories.add(ResponsCategory(it.subcat2Name, it.subcat2Id.toInt()))

            idcat = it.catId.toInt()
            idsubcat1 = it.subcat1Id.toInt()
            idsubcat2 = it.subcat2Id.toInt()

            namecat = it.catName
            namesubcat1 = it.subcat1Name
            namesubcat2 = it.subcat2Name


            binding!!.rcCategor.layoutManager =
                LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            val catAdapter = CatAdapter(categories)
            binding!!.rcCategor.adapter = catAdapter
            catAdapter.setOnClickCategoryItem(this)



            binding!!.apply {
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
                similarAdapter.setOnClickSimilarProduct(this)
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
                commentAdapter.setOnItemClickComment(this)
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
                        binding!!.coordinatorDetail,
                        "لطفا رنگ را انتخاب کنید",
                        Snackbar.LENGTH_SHORT
                    ).show()
            } else if (checkSize && !checkColor) {
                if (idSize != 0)
                    authViewModel.addToCart(idProduct!!, 0, idSize)
                else
                    Snackbar.make(
                        binding!!.coordinatorDetail,
                        "لطفا سایز را انتخاب کنید",
                        Snackbar.LENGTH_SHORT
                    ).show()

            } else if (!checkColor && !checkSize) {

                authViewModel.addToCart(idProduct!!, idColor, idSize)
            } else {
                if (idColor == 0 || idSize == 0) {
                    Snackbar.make(
                        binding!!.coordinatorDetail,
                        "لطفا سایز و رنگ را انتخاب کنید",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    authViewModel.addToCart(idProduct!!, idColor, idSize)
                }

            }
        }
        authViewModel.addToCartLiveData.observe(this) {
            Snackbar.make(binding!!.coordinatorDetail, it.message, Snackbar.LENGTH_SHORT).show()

            val countItem = EventBus.getDefault().getStickyEvent(ResponseCountCart::class.java)
            countItem?.let { it1 ->
                it1.count++
                EventBus.getDefault().postSticky(it1)
            }

        }


    }

    fun checkNetwork() {
        cld = ConnectionLiveData(application)
        cld.observe(this) { isConnected ->
            if (isConnected) {
                binding!!.coordinatorDetail.visibility = View.VISIBLE
                binding!!.lnrCheckNet.visibility = View.GONE

            } else {
                binding!!.coordinatorDetail.visibility = View.GONE
                binding!!.lnrCheckNet.visibility = View.VISIBLE
                binding!!.btnTryNet.setOnClickListener {
                    checkNetwork()
                }

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
                startActivity(Intent(this, ChartPriceActivity::class.java).apply {
                    putExtra(PRODUCT_ID, idProduct)
                })
            }
            COMPARE -> {
                startActivity(Intent(this, CompareCategoryActivity::class.java).apply {
                    putExtra(PRODUCT_ID, idProduct)
                })
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

    override fun onClickItem(typeCat: String) {
        when (typeCat) {
            namecat -> {
                val mFragment: Fragment
                mFragment = SubCatLevel1Fragment()
                val bundle =Bundle()
                bundle.putString("namecat", namecat)
                bundle.putInt(PRODUCT_ID, idcat!!)
                mFragment.arguments = bundle
                val fragmentManager: FragmentManager = supportFragmentManager
                fragmentManager.beginTransaction().replace(R.id.detail_framelayout, mFragment).commit()

//                startActivity(Intent(this, GeneralCategoryActivity::class.java).apply {
//                    putExtra("id", idcat)
//                    putExtra("namecat", namecat)
//                })
            }
            namesubcat1 -> {
//                startActivity(Intent(this, SubCat1Activity::class.java).apply {
//                    putExtra("id", idsubcat1)
//                    putExtra("namesubcat1", namesubcat1)
//                })


            }
            namesubcat2 -> {
//                startActivity(Intent(this, SubCat2Activity::class.java).apply {
//                    putExtra("id", idsubcat2)
//                    putExtra("namesubcat2", namesubcat2)
//
//                })

                startActivity(Intent(this,SubCat2Activity::class.java).apply {
                    putExtra(PRODUCT_ID,idsubcat2)
                    putExtra("namesubcat2", namesubcat2)
                })

            }
        }
    }

    override fun onClickItemSimilar(productId: Int) {
        startActivity(Intent(this,DetailActivity::class.java).apply {
            putExtra("id",productId)
        })
    }

    override fun onClickCommentProduct(commentId: Int) {
        startActivity(Intent(this, ShowCommentActivity::class.java).apply {
            putExtra("id", idProduct)
        })
    }
}