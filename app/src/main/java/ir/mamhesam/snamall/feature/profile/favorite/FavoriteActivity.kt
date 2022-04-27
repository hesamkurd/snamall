package ir.mamhesam.snamall.feature.profile.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.base.BaseActivity
import ir.mamhesam.snamall.databinding.ActivityFavoriteBinding
import ir.mamhesam.snamall.feature.home.detailproduct.DetailActivity
import ir.mamhesam.snamall.feature.profile.favorite.adapter.FavoriteAdapter
import ir.mamhesam.snamall.feature.profile.favorite.viewmodel.FavoriteViewModel
import ir.mamhesam.snamall.utils.PRODUCT_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FavoriteActivity : BaseActivity(),FavoriteAdapter.OnClickFavorite {
    lateinit var binding: ActivityFavoriteBinding
    val favoriteViewModel: FavoriteViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_favorite)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtTitle.text = "علاقه مندی ها"
        binding.imgBack.setOnClickListener {
            finish()
        }

        favoriteViewModel.favoriteLiveData.observe(this){
            val favoriteAdapter: FavoriteAdapter by inject { parametersOf(it) }
            binding.rcFavorite.layoutManager = LinearLayoutManager(this)
            binding.rcFavorite.adapter = favoriteAdapter
            favoriteAdapter.setOnClickFavoriteItem(this)
        }
        favoriteViewModel.emptyStateLiveData.observe(this){
            val parent = binding.root.findViewById<LinearLayout>(R.id.lnr_empty_favourite)
            if (it.show){
                val emptyState = showEmptyState(R.layout.layout_empty_state_favourite)
                emptyState?.let { view->
                    val txtEmpty = view.findViewById<TextView>(R.id.txt_state_favourite)
                    txtEmpty.text = getString(it.message)
                }
            }else{
                 parent?.let {it1->
                     it1.visibility = View.GONE
                 }
            }
        }
        favoriteViewModel.progressBarLiveData.observe(this){
            setProgressBar(it)
        }
    }

    override fun onClickFavoriteItem(id: Int) {
        startActivity(Intent(this,DetailActivity::class.java).apply {
            putExtra(PRODUCT_ID,id)
        })
    }

    override fun onStart() {
        super.onStart()
        favoriteViewModel.getFavorite()
    }
}