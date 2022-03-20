package ir.mamhesam.snamall.feature.home.detailproduct

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.mamhesam.snamall.base.BaseActivity
import ir.mamhesam.snamall.databinding.ActivityDetailBinding
import ir.mamhesam.snamall.feature.home.detailproduct.adapter.*
import ir.mamhesam.snamall.feature.home.detailproduct.viewmodel.DetailProductViewModel
import ir.mamhesam.snamall.utils.MiddleDividerItemDecoration
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DetailActivity : BaseActivity() {

    var binding: ActivityDetailBinding?=null
    val detailProductViewModel: DetailProductViewModel by viewModel { parametersOf(intent.getIntExtra("id",0)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        detailProductViewModel.detailProductLiveData.observe(this){
            val galleryAdapter: GalleryAdapter by inject { parametersOf(it.images) }
            binding!!.apply {
                galleryViewpager.adapter = galleryAdapter
                dotsIndicator.setViewPager2(galleryViewpager)
            }
            val animalNames = ArrayList<String>()
            animalNames.add(it.catName)
            animalNames.add(it.subcat1Name)
            animalNames.add(it.subcat2Name)
            binding!!.rcCategor.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
            val catAdapter= CatAdapter(animalNames)
            binding!!.rcCategor.adapter = catAdapter

            binding!!.apply {
                txtName.text = it.name
                txtBrandName.text = it.brandName
                txtSubBrandName.text = it.subBrandName
                productRating.rating = it.score
                txtSeller.text = it.sellerName
                txtWarranty.text = it.garantyDescription
                txtNumberProduct.text = it.number + " عدد موجود در انبار "

            }
            ///
            val colorAdapter: ColorAdapter by inject { parametersOf(it.productColors)}
            binding!!.rcColors.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            binding!!.rcColors.adapter = colorAdapter
            ///
            val sizeAdapter: SizeAdapter by inject { parametersOf(it.productSizes)}
            binding!!.rcSize.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            binding!!.rcSize.adapter = sizeAdapter
            ///
            val similarAdapter: SimilarAdapter by inject { parametersOf(it.similarProduct)}
            binding!!.rcSimilar.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            binding!!.rcSimilar.adapter = similarAdapter
           val itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL)
            binding!!.rcSimilar.addItemDecoration(itemDecoration)

            ///
            val propreAdapter: PropertiesAdapter by inject { parametersOf(it.sproperties) }
            binding!!.rcProperties.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            binding!!.rcProperties.adapter = propreAdapter

        }
        detailProductViewModel.progressBarLiveData.observe(this){
            setProgressBar(it)
        }




    }


}