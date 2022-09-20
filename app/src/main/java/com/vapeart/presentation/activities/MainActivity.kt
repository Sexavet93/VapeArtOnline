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
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavHost
import androidx.navigation.navOptions
import androidx.navigation.ui.setupWithNavController
import com.vapeart.R
import com.vapeart.data.room.SelectedItem
import com.vapeart.databinding.ActivityMainBinding
import com.vapeart.presentation.fragments.HomeFragmentDirections
import com.vapeart.presentation.utils.Navigator
import com.vapeart.presentation.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val SIGN_OUT_BUTTON = "Sign Out"

@AndroidEntryPoint
class MainActivity: AppCompatActivity(), Navigator {

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
    }

    override fun onStart() {
        super.onStart()
        initializePopUpMenu()
        setNavigationMenu()
        setViewModelObserver()
        setToolbarButtonsListeners()
    }

    private fun setToolbarButtonsListeners(){
        binding.menu.setOnClickListener{
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.dropdownMenu.setOnClickListener {
            popupMenu.show()
        }

        binding.call.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+994704030313"))
            startActivity(intent)
        }

        binding.cart.setOnClickListener{
            if(navController.currentDestination?.id != R.id.cartFragment){
                navController.navigate(R.id.cartFragment,null,navOptions { popUpTo(R.id.cartFragment){inclusive = true} })
            }
        }
    }

    private fun setNavigationMenu(){
        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.devices ->  navigateWithId(R.id.itemsReviewFragment,"devices")
                R.id.disposableDevices ->  navigateWithId(R.id.itemsReviewFragment,"disposable_devices")
                R.id.atomizers -> navigateWithId(R.id.itemsReviewFragment,"atomizers")
                R.id.cartridgeAndCoils -> navigateWithId(R.id.itemsReviewFragment,"cartridges_and_coils")
                R.id.regularLiquids -> navigateWithId(R.id.itemsReviewFragment,"regular_liquids")
                R.id.saltNicotineLiquids -> navigateWithId(R.id.itemsReviewFragment,"salt_nicotine_liquids")
            }
            true
        }
    }

    private fun setViewModelObserver(){
        viewModel.selectedItemLiveData.observe(this){
            setSelectedItemCount(it)
        }
    }

    private fun checkRegisteredUser(){
        if(!viewModel.isUserRegistered()){
            navigate(HomeFragmentDirections.actionHomeFragmentToSignInFragment(""))
            viewVisibility(false)
        } else viewVisibility(true)
    }

    override fun navigate(direction: NavDirections) {
        navController.navigate(direction)
    }

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun viewVisibility(isVisible: Boolean) {
        if(isVisible){
            binding.apply {
                bottomNavigation.visibility = View.VISIBLE
                toolBar.visibility = View.VISIBLE
            }
        }
        else {
            binding.apply {
                bottomNavigation.visibility = View.GONE
                toolBar.visibility = View.GONE
            }
        }
    }

    private fun navigateWithId(id: Int, query: String){
        navController.navigate(
            id,
            bundleOf("query" to query)
            ,navOptions { popUpTo(id){inclusive = true} }
        )
        binding.drawerLayout.closeDrawers()
    }

    private fun navControllerInitializer(){
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHost
        navController = navHost.navController
        binding.bottomNavigation.setupWithNavController(navController)
    }

    private fun initializePopUpMenu(){
        popupMenu = PopupMenu(this, binding.dropdownMenu)
        popupMenu.menu.add(SIGN_OUT_BUTTON)
        popupMenu.setOnMenuItemClickListener {
            when(it.toString()){
                SIGN_OUT_BUTTON -> {
                    viewModel.signOut()
                    navController.navigate(HomeFragmentDirections.actionHomeFragmentToSignInFragment(""))
                    viewVisibility(false)
                }
            }
            return@setOnMenuItemClickListener true
        }
    }

    private fun setSelectedItemCount(list: List<SelectedItem>){
        if(list.isNotEmpty()){
            var count = 0
            list.forEach{ count+= it.amount }
            binding.selectedItemCount.text = count.toString()
            binding.selectedItemCount.visibility = View.VISIBLE
        }else{
            binding.selectedItemCount.visibility = View.GONE
        }
    }
}