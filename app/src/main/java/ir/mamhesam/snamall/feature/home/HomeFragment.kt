package ir.mamhesam.snamall.feature.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import ir.mamhesam.snamall.base.BaseFragment
import ir.mamhesam.snamall.data.ResponseBanners
import ir.mamhesam.snamall.databinding.FragmentHomeBinding
import ir.mamhesam.snamall.feature.home.adapter.BannersAdapter
import ir.mamhesam.snamall.feature.home.adapter.GeneralCategoryAdapter
import ir.mamhesam.snamall.feature.home.viewmodel.HomeViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment : BaseFragment() {

    val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding
    val handler = Handler(Looper.myLooper()!!)
    var bannersSlider: List<ResponseBanners>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.bannersLiveData.observe(viewLifecycleOwner){
            bannersSlider = it
            val bannersAdapter: BannersAdapter by inject { parametersOf(it) }

            binding.viewPagerBanners.apply {
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
            binding.viewPagerBanners.adapter = bannersAdapter
            binding.dotsIndicator.setViewPager2(binding.viewPagerBanners)

            binding.viewPagerBanners.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
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
            binding.rcGeneralCategory.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            binding.rcGeneralCategory.adapter = generalCategoryAdapter


        }
    }

    private val sliderRunnable = Runnable {

        if (this == null) return@Runnable
        val index: Int = binding.viewPagerBanners.currentItem + 1
        binding.viewPagerBanners.currentItem = index
        if (index > bannersSlider!!.size - 1)
            binding.viewPagerBanners.currentItem = 0
    }
    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(sliderRunnable)

    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(sliderRunnable, 5000)
    }




}