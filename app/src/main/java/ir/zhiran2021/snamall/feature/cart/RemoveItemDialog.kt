package ir.zhiran2021.snamall.feature.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.zhiran2021.snamall.databinding.DialogRemoveBinding
import ir.zhiran2021.snamall.utils.NO
import ir.zhiran2021.snamall.utils.YES

class RemoveItemDialog:BottomSheetDialogFragment() {
    lateinit var binding: DialogRemoveBinding
    lateinit var onDialogRemove: OnDialogRemove

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogRemoveBinding.inflate(inflater,container, false)

        binding.btnYes.setOnClickListener {
            onDialogRemove.onClickItem(YES)
        }
        binding.btnNo.setOnClickListener {
            onDialogRemove.onClickItem(NO)
        }


        return binding.root

    }
    fun setOnClickRemove(onDialogRemove: OnDialogRemove){
        this.onDialogRemove = onDialogRemove
    }
    interface OnDialogRemove{
        fun onClickItem(type: String)
    }
}