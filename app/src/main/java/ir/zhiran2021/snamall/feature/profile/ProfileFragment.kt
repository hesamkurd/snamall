package ir.zhiran2021.snamall.feature.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.zhiran2021.snamall.base.BaseFragment
import ir.zhiran2021.snamall.databinding.FragmentProfileBinding
import ir.zhiran2021.snamall.feature.profile.address.AddressActivity
import ir.zhiran2021.snamall.feature.profile.auoth.AuthViewModel
import ir.zhiran2021.snamall.feature.profile.auoth.LoginActivity
import ir.zhiran2021.snamall.feature.profile.chargewallet.ChargeWalletActivity
import ir.zhiran2021.snamall.feature.profile.favorite.FavoriteActivity
import ir.zhiran2021.snamall.feature.profile.infouser.InfoUserActivity
import ir.zhiran2021.snamall.feature.profile.order.OrderActivity
import ir.zhiran2021.snamall.feature.profile.orderdelivery.OrderDeliveryActivity
import ir.zhiran2021.snamall.utils.UtilSocial
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : BaseFragment() {
     var binding: FragmentProfileBinding?=null

    val authViewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile, container, false)

        binding ?: run{
            binding = FragmentProfileBinding.inflate(inflater,container,false)

            binding!!.lnrCloud.setOnClickListener {
                startActivity(Intent(context,OrderActivity::class.java))
            }

            binding!!.lnrOrderHistory.setOnClickListener {
                startActivity(Intent(context,OrderDeliveryActivity::class.java))
            }

            binding!!.rltvFavourite.setOnClickListener {
                startActivity(Intent(context,FavoriteActivity::class.java))
            }
            binding!!.rltvAdress.setOnClickListener {
                startActivity(Intent(context,AddressActivity::class.java))
            }

            binding!!.rltvInfo.setOnClickListener {
                startActivity(Intent(context,InfoUserActivity::class.java))
            }

            binding!!.rltvWallet.setOnClickListener {
                startActivity(Intent(context,ChargeWalletActivity::class.java))
            }

            binding!!.imgInstagram.setOnClickListener {
                context?.let {
                    UtilSocial.instagram(it,"sna.mall")
                }
            }

            authViewModel.checkLoginLiveData.observe(viewLifecycleOwner){
                if (it){

                    binding!!.lnrLoginEmpty.visibility = View.GONE
                    binding!!.lnrProfile.visibility = View.VISIBLE
                    binding!!.rltvProfile.visibility = View.GONE
                }else{
                    binding!!.lnrLoginEmpty.visibility = View.VISIBLE
                    binding!!.lnrProfile.visibility = View.GONE
                    binding!!.rltvProfile.visibility = View.GONE
                }
            }
            binding!!.btnIntetnLogin.setOnClickListener {
                startActivity(Intent(context,LoginActivity::class.java))
            }

        }



        return binding!!.root
    }

    override fun onStart() {
        super.onStart()
        authViewModel.checkLogin()
    }

}