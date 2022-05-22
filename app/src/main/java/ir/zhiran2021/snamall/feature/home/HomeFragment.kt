package ir.zhiran2021.snamall.feature.home

import android.app.Application
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.dgreenhalgh.android.simpleitemdecoration.grid.GridDividerItemDecoration
import com.fondesa.recyclerviewdivider.dividerBuilder
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseFragment
import ir.zhiran2021.snamall.data.ConnectionLiveData
import ir.zhiran2021.snamall.data.ResponseBanners
import ir.zhiran2021.snamall.data.ResponseGeneralCategory
import ir.zhiran2021.snamall.databinding.FragmentHomeBinding
import ir.zhiran2021.snamall.feature.home.adapter.*
import ir.zhiran2021.snamall.feature.home.allamazing.AllAmazingActivity
import ir.zhiran2021.snamall.feature.home.detailproduct.DetailActivity
import ir.zhiran2021.snamall.feature.home.viewmodel.HomeViewModel
import ir.zhiran2021.snamall.feature.search.SearchActivity
import ir.zhiran2021.snamall.utils.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class HomeFragment : BaseFragment(),BannersType2Adapter.OnClickBannerType,
    AmazingAdapter.OnClickProduct,
    PopularAdapter.OnClickPopularItem,
    GeneralCategoryAdapter.OnClickGeneralCategory,
    AmazingAdapter.OnClickLast, BestSellAdapter.OnClickBestSellItem{

    val homeViewModel: HomeViewModel by viewModel()
    var binding: FragmentHomeBinding?=null
    val handler = Handler(Looper.myLooper()!!)
    var bannersSlider: List<ResponseBanners>? = null
    val general: ResponseGeneralCategory?=null
    var amazingAdapter : AmazingAdapter?=null
    private lateinit var cld:ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
       binding ?: run{

           checkNetwork()
           binding = FragmentHomeBinding.inflate(inflater,container,false)

           binding!!.rltvSearch.setOnClickListener {
               startActivity(Intent(context,SearchActivity::class.java))
           }
           homeViewModel.bannersLiveData.observe(viewLifecycleOwner){
               bannersSlider = it
               val bannersAdapter: BannersAdapter by inject { parametersOf(it) }

               binding!!.viewPagerBanners.apply {
                   orientation = ViewPager2.ORIENTATION_HORIZONTAL
                   clipToPadding = false
                   clipChildren = false
                   offscreenPageLimit = 3
                   getChildAt(0)!!.overScrollMode = RecyclerView.OVER_SCROLL_ALWAYS

                   val transformer = CompositePageTransformer()
                   transformer.addTransformer(MarginPageTransformer(20))
                   transformer.addTransformer { page, position ->
                       val r = 1 - Math.abs(position)
                       page.scaleY = 0.85f + r * 0.1f
                   }
                   setPageTransformer(transformer)

               }

               binding!!.viewPagerBanners.adapter = bannersAdapter
               binding!!.dotsIndicator.setViewPager2(binding!!.viewPagerBanners)

               binding!!.viewPagerBanners.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                   override fun onPageSelected(position: Int) {
                       super.onPageSelected(position)

                       handler.removeCallbacks(sliderRunnable)
                       handler.postDelayed(sliderRunnable, 5000)
                   }
               })

           }
           homeViewModel.progressBarLiveData.observe(viewLifecycleOwner){
               setProgressBar(it)
           }

           homeViewModel.generalCategoryLiveData.observe(viewLifecycleOwner){
               val generalCategoryAdapter: GeneralCategoryAdapter by inject { parametersOf(it) }
              binding!!.rcGeneralCategory.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
               binding!!.rcGeneralCategory.adapter = generalCategoryAdapter


               generalCategoryAdapter.setOnClickGeneral(this)

           }
           homeViewModel.amazingProductsLiveData.observe(viewLifecycleOwner){
               val amazingAdapter: AmazingAdapter by inject { parametersOf(it) }
               binding!!.rcAmazingProduct.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
               binding!!.rcAmazingProduct.adapter = amazingAdapter
               amazingAdapter.setOnClickProductItem(this)
               amazingAdapter.setOnClickItemLast(this)
           }
           homeViewModel.popularProductLiveData.observe(viewLifecycleOwner){
               val popularAdapter: PopularAdapter by inject { parametersOf(it) }
               binding!!.rcPopularProduct.layoutManager = GridLayoutManager(context,3)

//               val horizontalDivider: Drawable? = ContextCompat.getDrawable(requireContext(),R.drawable.line_divider)
//               val verticalDivider: Drawable? = ContextCompat.getDrawable(requireContext(),R.drawable.line_divider)
             //  binding!!.rcPopularProduct.addItemDecoration(DividerItemDecorator())

               requireContext().dividerBuilder()
                  // .color(Color.BLACK)
                   .drawableRes(R.drawable.line_divider)
                   .size(1,TypedValue.COMPLEX_UNIT_DIP)
                  // .showSideDividers()
                   //.showLastDivider()
                   .build()
                   .addTo(binding!!.rcPopularProduct)

               binding!!.rcPopularProduct.adapter = popularAdapter
               popularAdapter.setOnPopularClick(this)
           }

           homeViewModel.bannerType2LiveData.observe(viewLifecycleOwner){
               val bannerType2Adapter: BannersType2Adapter by inject { parametersOf(it) }
               binding!!.rcBannerType2.layoutManager = GridLayoutManager(requireContext(),2)
               binding!!.rcBannerType2.adapter = bannerType2Adapter
               bannerType2Adapter.setOnClickBannersType(this)
           }

           homeViewModel.bestSellLiveData.observe(viewLifecycleOwner){
               val bestSellAdapter: BestSellAdapter by inject { parametersOf(it) }
               binding!!.rcBestSell.layoutManager = GridLayoutManager(requireContext(),3,GridLayoutManager.HORIZONTAL,false)
               binding!!.rcBestSell.adapter = bestSellAdapter
               bestSellAdapter.setOnClickItem(this)
           }


       }
        return binding!!.root
    }

    fun checkNetwork() {
        cld = ConnectionLiveData(requireActivity().application)
        cld.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) {
                binding!!.homeLayout.visibility = View.VISIBLE
                binding!!.lnrCheckNetHome.visibility = View.GONE
            } else {
                binding!!.homeLayout.visibility = View.GONE
                binding!!.lnrCheckNetHome.visibility = View.VISIBLE
                binding!!.btnTryNet.setOnClickListener {
                    checkNetwork()
                }

            }
        }
    }
    private val sliderRunnable = Runnable {

        if (this == null) return@Runnable
        val index: Int = binding!!.viewPagerBanners.currentItem + 1
        binding!!.viewPagerBanners.currentItem = index
        if (index > bannersSlider!!.size - 1)
            binding!!.viewPagerBanners.currentItem = 0
    }
    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(sliderRunnable)


    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(sliderRunnable, 5000)

    }

    override fun onClickBannerType(type: String, link: String) {
        when(type){
            TYPE_ONE->{

            }
            TYPE_TWO->{

            }

        }
    }

    override fun onClickProduct(productId: Int) {
        startActivity(Intent(context,DetailActivity::class.java).apply {
            putExtra("id", productId)
        })
    }

    override fun onClickPopular(productId: Int) {
        startActivity(Intent(context,DetailActivity::class.java).apply {
            putExtra("id", productId)
        })
    }

    override fun onClickGeneralItem(generalCatId: Int, generalTitle: String) {
        val bundle=Bundle()
        bundle.putInt(PRODUCT_ID,generalCatId)
        bundle.putString("title",generalTitle)
        findNavController().navigate(R.id.action_homeFragment_to_subCatLevel1Fragment,bundle)
    }

    override fun onClickLastItem() {
        startActivity(Intent(context,AllAmazingActivity::class.java))
    }

    override fun onClickItemBestSell(productId: Int) {
        startActivity(Intent(context,DetailActivity::class.java).apply {
            putExtra("id",productId)
        })
    }


}