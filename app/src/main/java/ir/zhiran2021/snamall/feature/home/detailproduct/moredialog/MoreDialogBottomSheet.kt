package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.zhiran2021.snamall.databinding.DialogMoreBinding
import ir.zhiran2021.snamall.utils.CHART
import ir.zhiran2021.snamall.utils.COMPARE

class MoreDialogBottomSheet: BottomSheetDialogFragment() {

    var myView: View? = null
    lateinit var onClickMoreDialog: OnClickMoreDialog
    var binding : DialogMoreBinding?=null
    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

            binding = DialogMoreBinding.inflate(inflater,container,false)
            binding!!.lnrComparePrice.setOnClickListener{
                onClickMoreDialog.onClickMore(COMPARE)
            }
            binding!!.lnrChartPrice.setOnClickListener {
                onClickMoreDialog.onClickMore(CHART)
            }


        return binding!!.root
    }
    fun setOnClicKDialog( onClickMoreDialog: OnClickMoreDialog){
        this.onClickMoreDialog = onClickMoreDialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    interface OnClickMoreDialog{
        fun onClickMore(type:String)
    }
}