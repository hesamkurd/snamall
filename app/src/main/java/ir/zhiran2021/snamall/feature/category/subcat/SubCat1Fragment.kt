package ir.zhiran2021.snamall.feature.category.subcat

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.databinding.FragmentSubCat1Binding
import ir.zhiran2021.snamall.feature.category.brandproduct.BrandActivity
import ir.zhiran2021.snamall.feature.category.subcat.adapter.PopularBrandAdapter
import ir.zhiran2021.snamall.feature.category.subcat.adapter.SubCatProductAdapter
import ir.zhiran2021.snamall.feature.category.subcat.adapter.SubCategoryAdapter
import ir.zhiran2021.snamall.feature.category.subcat.viewmodel.SubCatViewModel
import ir.zhiran2021.snamall.utils.PRODUCT_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SubCat1Fragment : Fragment(),PopularBrandAdapter.OnClickBrand {
    var binding : FragmentSubCat1Binding?=null
    val subCatViewModel: SubCatViewModel by viewModel { parametersOf(arguments?.getInt(PRODUCT_ID,0)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sub_cat1, container, false)
        binding ?: run{
            binding = FragmentSubCat1Binding.inflate(inflater,container,false)

            subCatViewModel.subCatLiveData.observe(viewLifecycleOwner){
                val subCatAdapter: SubCategoryAdapter by inject { parametersOf(it) }
                binding!!.rcSubCat.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
                binding!!.rcSubCat.adapter = subCatAdapter
            }

            subCatViewModel.popularBrandLiveData.observe(viewLifecycleOwner){
                val popularBrandAdapter: PopularBrandAdapter by inject { parametersOf(it) }
                binding!!.rcPopularBrand.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
                binding!!.rcPopularBrand.adapter = popularBrandAdapter
                popularBrandAdapter.setOnClickBrandItem(this)
            }

            subCatViewModel.subCatProductLiveData.observe(viewLifecycleOwner){
                val subCatProductAdapter: SubCatProductAdapter by inject { parametersOf(it) }
                binding!!.rcSubCatProduct.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
                binding!!.rcSubCatProduct.adapter = subCatProductAdapter
            }



        }


        return binding!!.root
    }

    override fun onClickBrandItem(brandName: String) {
        startActivity(Intent(context,BrandActivity::class.java).apply {
            putExtra(PRODUCT_ID,brandName)
        })
    }

}