package ir.mamhesam.snamall.feature.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.databinding.FragmentProfileBinding
import ir.mamhesam.snamall.feature.profile.address.AddressActivity
import ir.mamhesam.snamall.feature.profile.auoth.AuthViewModel
import ir.mamhesam.snamall.feature.profile.auoth.LoginActivity
import ir.mamhesam.snamall.feature.profile.chargewallet.ChargeWalletActivity
import ir.mamhesam.snamall.feature.profile.favorite.FavoriteActivity
import ir.mamhesam.snamall.feature.profile.infouser.InfoUserActivity
import ir.mamhesam.snamall.feature.profile.order.OrderActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {
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