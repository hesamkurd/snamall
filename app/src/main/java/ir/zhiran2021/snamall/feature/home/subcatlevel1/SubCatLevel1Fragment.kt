package ir.zhiran2021.snamall.feature.home.subcatlevel1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseFragment
import ir.zhiran2021.snamall.databinding.FragmentSubCatLevel1Binding
import ir.zhiran2021.snamall.feature.home.subcatlevel1.adapter.SubCatLevel1Adapter
import ir.zhiran2021.snamall.feature.home.subcatlevel1.viewmodel.SubCatLevel1ViewModel
import ir.zhiran2021.snamall.utils.PRODUCT_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SubCatLevel1Fragment : BaseFragment(), SubCatLevel1Adapter.OnClickSubCatLevel {

    var binding : FragmentSubCatLevel1Binding?=null
    val subCatLevel1ViewModel: SubCatLevel1ViewModel by viewModel { parametersOf(arguments?.getInt(
        PRODUCT_ID,0)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_sub_cat_level1, container, false)
        binding ?: run{
            binding = FragmentSubCatLevel1Binding.inflate(inflater,container,false)

            val title = arguments?.getString("namecat","")
            binding!!.txtTitle.text = title.toString()

            subCatLevel1ViewModel.subCatLevelLiveData.observe(viewLifecycleOwner){
                val subCatLevel1Adapter: SubCatLevel1Adapter by inject { parametersOf(it) }
                binding!!.rcSubCatLevel.layoutManager = GridLayoutManager(context,3)
                binding!!.rcSubCatLevel.adapter = subCatLevel1Adapter
                subCatLevel1Adapter.setOnClickItemSubCatLevel(this)

            }
            subCatLevel1ViewModel.progressBarLiveData.observe(viewLifecycleOwner){
                setProgressBar(it)
            }

        }
        return binding!!.root
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            SubCatLevel1Fragment().apply {

            }
    }

    override fun onClickGeneralItem(generalCatId: Int) {
        val bundle= Bundle()
        bundle.putInt(PRODUCT_ID, generalCatId)
        findNavController().navigate(R.id.action_subCatLevel1Fragment_to_subCat1Fragment2,bundle)

    }
}