package ir.zhiran2021.snamall.feature.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.base.BaseFragment
import ir.zhiran2021.snamall.data.ProductItemItem
import ir.zhiran2021.snamall.databinding.FragmentCartBinding
import ir.zhiran2021.snamall.feature.cart.adapter.CartListAdapter
import ir.zhiran2021.snamall.feature.cart.nextlevel.NextLevelActivity
import ir.zhiran2021.snamall.feature.cart.viewmodel.CartListViewModel
import ir.zhiran2021.snamall.feature.profile.auoth.AuthViewModel
import ir.zhiran2021.snamall.feature.profile.auoth.LoginActivity
import ir.zhiran2021.snamall.utils.NO
import ir.zhiran2021.snamall.utils.YES
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CartFragment : BaseFragment(), CartListAdapter.OnClickRemoveItem,
    RemoveItemDialog.OnDialogRemove {

    val cartListViewModel: CartListViewModel by viewModel()
    val authViewModel: AuthViewModel by viewModel()
    lateinit var binding: FragmentCartBinding
    var cartListAdapterPublic: CartListAdapter? = null
    val compositeDisposable = CompositeDisposable()
    lateinit var removeItemDialog: RemoveItemDialog
    lateinit var cartItemRemove: ProductItemItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_cart, container, false)

        binding = FragmentCartBinding.inflate(inflater, container, false)

        binding.btnNextCart.setOnClickListener {
            startActivity(Intent(context, NextLevelActivity::class.java))
        }


        cartListViewModel.cartListLiveData.observe(viewLifecycleOwner) {
            val cartListAdapter: CartListAdapter by inject { parametersOf(it) }
            cartListAdapterPublic = cartListAdapter
            binding.rcCart.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            binding.rcCart.adapter = cartListAdapter
            cartListAdapter.setOnClickItemRemove(this)
        }

        cartListViewModel.progressBarLiveData.observe(viewLifecycleOwner) {
            setProgressBar(it)
        }
        cartListViewModel.payablePriceLiveData.observe(viewLifecycleOwner) {
            cartListAdapterPublic?.let { cartListAdapter ->
                cartListAdapter.publicAllPayablePrice = it
            }

        }
        cartListViewModel.totalPriceLiveData.observe(viewLifecycleOwner) {
            cartListAdapterPublic?.let { cartListAdapter ->
                cartListAdapter.publicTotalAllPrice = it
                cartListAdapter.notifyDataSetChanged()
            }

        }
        cartListViewModel.totalOfPriceLiveData.observe(viewLifecycleOwner) {
            cartListAdapterPublic?.let { cartListAdapter ->
                cartListAdapter.publicAllOffPrIce = it
            }

        }
        cartListViewModel.emptyStateLiveData.observe(viewLifecycleOwner) {
            val parent = view?.findViewById<LinearLayout>(R.id.lnr_empty)
            if (it.show) {
                val emptyState = showEmptyState(R.layout.layout_empty_state)
                emptyState?.let { view ->

                    val txtMessage = view.findViewById<TextView>(R.id.txt_state)
                    val btnLogin = view.findViewById<Button>(R.id.btn_empty_state)
                    txtMessage!!.text = getString(it.message)
                    if (it.showButton) {
                        btnLogin!!.visibility = View.VISIBLE
                    } else {
                        btnLogin!!.visibility = View.GONE

                    }
                    btnLogin.setOnClickListener {
                        startActivity(Intent(context, LoginActivity::class.java))
                    }
                }
            } else {
                parent?.let { it1 ->
                    it1.visibility = View.GONE
                }
            }

        }


        return binding.root
    }

    override fun onStart() {
        super.onStart()
        authViewModel.checkLogin()
        cartListViewModel.refresh()
    }

    override fun onClickItem(cartItem: ProductItemItem) {
        cartItemRemove = cartItem
        removeItemDialog = RemoveItemDialog()
        removeItemDialog.show(parentFragmentManager, null)
        removeItemDialog.setOnClickRemove(this)
    }

    override fun onClickSumItem(cartItem: ProductItemItem, newCount: Int) {
        cartListAdapterPublic!!.sumItemCount(cartItem, newCount)
        val new = newCount + 1
        cartListViewModel.changeCountIte(cartItem, new, true).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    override fun onClickMinusItem(cartItem: ProductItemItem, newCount: Int) {
        cartListAdapterPublic!!.minusItemCount(cartItem, newCount)
        val new = newCount - 1
        cartListViewModel.changeCountIte(cartItem, new, false).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    override fun onClickItem(type: String) {
        when (type) {
            YES -> {
                cartListViewModel.removeFromCart(cartItemRemove).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : CompletableObserver {
                        override fun onSubscribe(d: Disposable) {
                            compositeDisposable.add(d)
                        }

                        override fun onComplete() {
                            removeItemDialog.dismiss()
                        }

                        override fun onError(e: Throwable) {
                        }

                    })
            }
            NO -> {
                removeItemDialog.dismiss()
            }
        }
    }


}