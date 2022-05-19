package ir.zhiran2021.snamall.feature.cart.nextlevel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.zhiran2021.snamall.databinding.DialogBuyBinding
import ir.zhiran2021.snamall.utils.DIRECT
import ir.zhiran2021.snamall.utils.PriceConverter
import ir.zhiran2021.snamall.utils.WALLET

class BuyDialog:BottomSheetDialogFragment() {
    lateinit var binding: DialogBuyBinding
    lateinit var onDialogBuy: OnDialogBuy
    var wallet:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       wallet= arguments?.getString(WALLET)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogBuyBinding.inflate(inflater,container, false)
        binding.txtPrice.text = " مبلغ کیف پول شما : " +PriceConverter.priceConvert(wallet.toString())

        binding.txtWallet.setOnClickListener {
            binding.rltvBuy.visibility = View.VISIBLE
        }
        binding.txtDirect.setOnClickListener {
            onDialogBuy.onClickBuy(DIRECT)
        }
        binding.txtBuyWallet.setOnClickListener {
            onDialogBuy.onClickBuy(WALLET)

        }


        return binding.root

    }
    companion object{
        fun newInstance(wallet:String) =
            BuyDialog().apply {
                arguments = Bundle().apply {
                    putString(WALLET,wallet)
                }
            }
    }
    fun setOnBuyDialog(onDialogBuy: OnDialogBuy){
        this.onDialogBuy = onDialogBuy
    }
    interface OnDialogBuy{
        fun onClickBuy(type: String)
    }
}