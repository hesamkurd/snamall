package ir.mamhesam.snamall.feature.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.base.BaseFragment
import ir.mamhesam.snamall.data.ResponseBanners
import ir.mamhesam.snamall.databinding.FragmentHomeBinding
import ir.mamhesam.snamall.feature.home.adapter.*
import ir.mamhesam.snamall.feature.home.detailproduct.DetailActivity
import ir.mamhesam.snamall.feature.home.viewmodel.HomeViewModel
import ir.mamhesam.snamall.utils.DividerItemDecorator
import ir.mamhesam.snamall.utils.TYPE_ONE
import ir.mamhesam.snamall.utils.TYPE_TWO
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class HomeFragment : BaseFragment(),BannersType2Adapter.OnClickBannerType, AmazingAdapter.OnClickProduct {

    val homeViewModel: HomeViewModel by viewModel()
    var binding: FragmentHomeBinding?=null
    val handler = Handler(Looper.myLooper()!!)
    var bannersSlider: List<ResponseBanners>? = null

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
           binding = FragmentHomeBinding.inflate(inflater,container,false)
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


           }
           homeViewModel.amazingProductsLiveData.observe(viewLifecycleOwner){
               val amazingAdapter: AmazingAdapter by inject { parametersOf(it) }
               binding!!.rcAmazingProduct.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
               binding!!.rcAmazingProduct.adapter = amazingAdapter
               amazingAdapter.setOnClickProductItem(this)
           }
           homeViewModel.popularProductLiveData.observe(viewLifecycleOwner){
               val popularAdapter: PopularAdapter by inject { parametersOf(it) }
               binding!!.rcPopularProduct.layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
               val dividerItemDecoration: ItemDecoration = DividerItemDecorator(
                   ContextCompat.getDrawable(requireContext(), R.drawable.line_divider)!!)

               binding!!.rcPopularProduct.addItemDecoration(dividerItemDecoration)
               binding!!.rcPopularProduct.adapter = popularAdapter
           }

           homeViewModel.bannerType2LiveData.observe(viewLifecycleOwner){
               val bannerType2Adapter: BannersType2Adapter by inject { parametersOf(it) }
               binding!!.rcBannerType2.layoutManager = GridLayoutManager(requireContext(),2)
               binding!!.rcBannerType2.adapter = bannerType2Adapter
               bannerType2Adapter.setOnClickBannersType(this)
           }
       }
        return binding!!.root
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


}