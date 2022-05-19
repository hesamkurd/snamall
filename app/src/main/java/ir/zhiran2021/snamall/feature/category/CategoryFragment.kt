package ir.zhiran2021.snamall.feature.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseFragment
import ir.zhiran2021.snamall.databinding.FragmentCategoryBinding
import ir.zhiran2021.snamall.feature.category.adapter.CategoryAdapter
import ir.zhiran2021.snamall.feature.category.viewmodel.CategoriesViewModel
import ir.zhiran2021.snamall.utils.PRODUCT_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CategoryFragment : BaseFragment(),CategoryAdapter.OnClickCategory {

    var binding: FragmentCategoryBinding?=null
    val categoriesViewModel: CategoriesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_category, container, false)

        binding ?: run{
            binding = FragmentCategoryBinding.inflate(inflater,container,false)

            val parent= binding!!.root.findViewById<LinearLayout>(R.id.lnr_category_container)
            categoriesViewModel.categoriesLiveData.observe(viewLifecycleOwner){
                for (item in it){
                    val txtTitle=TextView(requireContext())
                    txtTitle.text = item.title

                    parent.addView(txtTitle)
                    val rcCategory = RecyclerView(requireContext())
                    rcCategory.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
                    val categoryAdapter: CategoryAdapter by inject { parametersOf(item.subcat) }
                    rcCategory.adapter = categoryAdapter
                    parent.addView(rcCategory)
                    categoryAdapter.setOnClickCat(this)
                }
            }

            categoriesViewModel.progressBarLiveData.observe(viewLifecycleOwner){
                setProgressBar(it)
            }

        }

        return binding!!.root
    }

    override fun onClickCatItem(generalCatId: Int) {
        val bundle = Bundle()
        bundle.putInt(PRODUCT_ID,generalCatId)
        findNavController().navigate(R.id.action_categoryFragment_to_subCat1Fragment,bundle)
    }


}