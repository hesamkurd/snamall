package ir.mamhesam.snamall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.color.MaterialColors
import ir.mamhesam.snamall.data.ResponseCountCart
import ir.mamhesam.snamall.databinding.ActivityMainBinding
import ir.mamhesam.snamall.utils.setupWithNavController
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * An activity that inflates a layout that has a [BottomNavigationView].
 */
class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by viewModel()
    private var currentNavController: LiveData<NavController>? = null
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_main)*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        EventBus.getDefault().register(this)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()

        } // Else, need to wait for onRestoreInstanceState
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()

    }

    /**
     * Called on first creation and when restoring state.
     */

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = binding.bottomNav
        val navGraphIds = listOf(
            R.navigation.home,
            R.navigation.category,
            R.navigation.cart,
            R.navigation.profile
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
//        controller.observe(this, Observer { navController ->
//            setupActionBarWithNavController(navController)
//        })
        currentNavController = controller



    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun countCart(countItem: ResponseCountCart){
        val badge = binding.bottomNav.getOrCreateBadge(R.id.cart)
        badge.backgroundColor = MaterialColors.getColor(binding.bottomNav, com.mukesh.R.attr.colorPrimary)
        badge.badgeGravity = BadgeDrawable.TOP_START
        badge.verticalOffset = 5
        badge.horizontalOffset = 5
        badge.number = countItem.count
        badge.isVisible =  countItem.count>0
    }


    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getCount()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

}