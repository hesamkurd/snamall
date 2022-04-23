package ir.mamhesam.snamall.feature.home.subcatlevel1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.base.BaseFragment
import ir.mamhesam.snamall.data.ResponseSubCatLevel1
import ir.mamhesam.snamall.databinding.FragmentSubCatLevel1Binding
import ir.mamhesam.snamall.feature.home.subcatlevel1.adapter.SubCatLevel1Adapter
import ir.mamhesam.snamall.feature.home.subcatlevel1.viewmodel.SubCatLevel1ViewModel
import ir.mamhesam.snamall.utils.PRODUCT_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SubCatLevel1Fragment : BaseFragment() {

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

            val title = arguments?.getString("title","")
            binding!!.txtTitle.text = title.toString()

            subCatLevel1ViewModel.subCatLevelLiveData.observe(viewLifecycleOwner){
                val subCatLevel1Adapter: SubCatLevel1Adapter by inject { parametersOf(it) }
                binding!!.rcSubCatLevel.layoutManager = GridLayoutManager(context,3)
                binding!!.rcSubCatLevel.adapter = subCatLevel1Adapter

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
}