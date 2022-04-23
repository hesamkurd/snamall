package ir.mamhesam.snamall.feature.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.databinding.DialogMoreBinding
import ir.mamhesam.snamall.databinding.DialogRemoveBinding
import ir.mamhesam.snamall.utils.NO
import ir.mamhesam.snamall.utils.YES

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