package com.vapeart.presentation.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavHost
import androidx.navigation.navOptions
import com.vapeart.R
import com.vapeart.databinding.ActivityMainBinding
import com.vapeart.domain.models.SelectedItem
import com.vapeart.presentation.fragments.HomeFragmentDirections
import com.vapeart.presentation.utils.DeviceCategory
import com.vapeart.presentation.utils.Navigator
import com.vapeart.presentation.utils.ViewsManager
import com.vapeart.presentation.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val SIGN_OUT_BUTTON = "Sign Out"

@AndroidEntryPoint
class MainActivity: AppCompatActivity(), Navigator, ViewsManager{

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var popupMenu: PopupMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_VapeArt)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navControllerInitializer()
        checkRegisteredUser()
        setBottomNavigationListener()
    }

    override fun onStart() {
        super.onStart()
        initializePopUpMenu()
        setNavigationMenu()
        setViewModelObserver()
        setToolbarButtonsListeners()
    }

    private fun setBottomNavigationListener(){
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.homeFragment -> {
                    if(navController.currentDestination?.id != R.id.homeFragment)
                        navigateWithId(R.id.homeFragment)
                }
                R.id.wishListFragment -> {
                    if(navController.currentDestination?.id != R.id.wishListFragment)
                        navigateWithId(R.id.wishListFragment)
                }
                R.id.cartFragment -> {
                    if(navController.currentDestination?.id != R.id.cartFragment)
                        navigateWithId(R.id.cartFragment)
                }
                R.id.searchFragment -> {
                    if(navController.currentDestination?.id != R.id.searchFragment)
                        navigateWithId(R.id.searchFragment)
                }
            }
            true
        }
    }

    private fun setToolbarButtonsListeners() {
        binding.menu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.dropdownMenu.setOnClickListener {
            popupMenu.show()
        }

        binding.call.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:" + getString(R.string.seller_phone)))
            startActivity(intent)
        }

        binding.cart.setOnClickListener {
            if (navController.currentDestination?.id != R.id.cartFragment) {
                navController.navigate(
                    R.id.cartFragment,
                    null,
                    navOptions { popUpTo(R.id.cartFragment) { inclusive = true } })
                bottomNavigationSelectedItem(R.id.cartFragment)
            }
        }
    }

    private fun setNavigationMenu() {
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.devices -> navigateWithId(R.id.itemsReviewFragment, DeviceCategory.DEVICES)
                R.id.disposableDevices -> navigateWithId(R.id.itemsReviewFragment, DeviceCategory.DISPOSABLE_DEVICES)
                R.id.atomizers -> navigateWithId(R.id.itemsReviewFragment, DeviceCategory.ATOMIZERS)
                R.id.cartridgeAndCoils -> navigateWithId(R.id.itemsReviewFragment, DeviceCategory.CARTRIDGES_AND_COILS)
                R.id.regularLiquids -> navigateWithId(R.id.itemsReviewFragment, DeviceCategory.REGULAR_LIQUIDS)
                R.id.saltNicotineLiquids -> navigateWithId(R.id.itemsReviewFragment, DeviceCategory.SALT_NICOTINE_LIQUIDS)
            }
            true
        }
    }

    private fun navigateWithId(id: Int, query: String) {
        navController.navigate(id, bundleOf("query" to query),
            navOptions { popUpTo(id){inclusive = true}})
        binding.drawerLayout.closeDrawers()
    }

    private fun navigateWithId(id: Int) {
        val popUpToId = navController.currentDestination?.id ?: 0
        navController.navigate(id, null, navOptions { popUpTo(popUpToId) { inclusive = true } })
        binding.drawerLayout.closeDrawers()
    }

    private fun setViewModelObserver() {
        viewModel.selectedItemLiveData.observe(this) {
            cartIndicator(it)
        }
    }

    private fun checkRegisteredUser() {
        if (!viewModel.isUserRegistered()) {
            navigate(HomeFragmentDirections.actionHomeFragmentToSignInFragment())
            drawerLayoutLock(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            toolBarAndBottomNavigationBarVisibility(false)
        } else {
            toolBarAndBottomNavigationBarVisibility(true)
        }
    }

    override fun navigate(direction: NavDirections) {
        navController.navigate(direction)
    }

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun toolBarAndBottomNavigationBarVisibility(isVisible: Boolean) {
        if (isVisible) {
            binding.apply {
                bottomNavigation.visibility = View.VISIBLE
                toolBar.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                bottomNavigation.visibility = View.GONE
                toolBar.visibility = View.GONE
            }
        }
    }

    private fun navControllerInitializer() {
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHost
        navController = navHost.navController
    }

    private fun initializePopUpMenu() {
        popupMenu = PopupMenu(this, binding.dropdownMenu)
        popupMenu.menu.add(SIGN_OUT_BUTTON)
        popupMenu.setOnMenuItemClickListener {
            when (it.toString()) {
                SIGN_OUT_BUTTON -> {
                    viewModel.signOut()
                    navigateWithId(R.id.signInFragment)
                    drawerLayoutLock(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
            }
            return@setOnMenuItemClickListener true
        }
    }

    private fun cartIndicator(list: List<SelectedItem>) {
        if (list.isNotEmpty()) {
            binding.cartIndicator.visibility = View.VISIBLE
        } else {
            binding.cartIndicator.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isOpen)
            binding.drawerLayout.closeDrawers()
        else if(navController.currentDestination?.id == R.id.signInFragment)
            finish()
        else {
            super.onBackPressed()
            when(navController.currentDestination?.id){
                R.id.homeFragment -> bottomNavigationSelectedItem(R.id.homeFragment)
                R.id.wishListFragment -> bottomNavigationSelectedItem(R.id.wishListFragment)
                R.id.cartFragment -> bottomNavigationSelectedItem(R.id.cartFragment)
                R.id.searchFragment -> bottomNavigationSelectedItem(R.id.searchFragment)
            }
        }
    }

    private fun bottomNavigationSelectedItem(itemId: Int){
        binding.bottomNavigation.selectedItemId = itemId
    }

    override fun drawerLayoutLock(lockMode: Int) {
        binding.drawerLayout.setDrawerLockMode(lockMode)
    }

}