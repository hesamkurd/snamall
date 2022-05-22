package ir.zhiran2021.snamall.feature.category

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseFragment
import ir.zhiran2021.snamall.data.ResponseCategories
import ir.zhiran2021.snamall.data.SubcatItem
import ir.zhiran2021.snamall.databinding.FragmentCategoryBinding
import ir.zhiran2021.snamall.feature.category.adapter.CategoryAdapter
import ir.zhiran2021.snamall.feature.category.adapter.CategoryChildAdapter
import ir.zhiran2021.snamall.feature.category.viewmodel.CategoriesViewModel
import ir.zhiran2021.snamall.feature.search.SearchActivity
import ir.zhiran2021.snamall.services.ImageLoadService
import ir.zhiran2021.snamall.utils.PRODUCT_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CategoryFragment : BaseFragment() {

    var binding: FragmentCategoryBinding?=null
    val categoriesViewModel: CategoriesViewModel by viewModel()
    lateinit var categoryChildAdapter: CategoryChildAdapter
    var subCat: List<SubcatItem>? = null
    val imageLoadService: ImageLoadService? = null
    //lateinit var categoryChildAdapter:CategoryChildAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_category, container, false)

        binding ?: run{
            binding = FragmentCategoryBinding.inflate(inflater,container,false)

            categoriesViewModel.categoriesLiveData.observe(viewLifecycleOwner){

               val categoryAdapter: CategoryAdapter by inject { parametersOf(it)}
                binding!!.rcCategory.layoutManager = LinearLayoutManager(requireContext())
                binding!!.rcCategory.adapter = categoryAdapter

            }
            binding!!.rltvSearch.setOnClickListener {
                startActivity(Intent(context, SearchActivity::class.java))

            }

            categoriesViewModel.progressBarLiveData.observe(viewLifecycleOwner){
                setProgressBar(it)
            }

        }

        return binding!!.root
    }



}