package com.vapeart.presentation.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavHost
import androidx.navigation.ui.setupWithNavController
import com.vapeart.R
import com.vapeart.databinding.ActivityMainBinding
import com.vapeart.presentation.Navigator
import com.vapeart.presentation.fragments.HomeFragmentDirections
import com.vapeart.presentation.fragments.SignInFragmentDirections
import com.vapeart.presentation.viewmodels.MainActivityViewModel

private const val SIGN_OUT_BUTTON = "Sign Out"

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
//        setTheme(R.style.Theme_VapeArt)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        navControllerInitializer()
        checkRegisteredUser()
        setContentView(binding.root)

        binding.bottomNavigation.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()
        setToolBarDropdownMenu()
        setToolBarMenuButton()
    }

    private fun checkRegisteredUser(){
        if(viewModel.isUserRegistered()){
//            viewVisibility(false)
            navigate(SignInFragmentDirections.actionSignInFragmentToHomeFragment())
        } else viewVisibility(false)
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

    private fun navControllerInitializer(){
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHost
        navController = navHost.navController
    }

    private fun setToolBarDropdownMenu(){
        val popupMenu = PopupMenu(this, binding.dropdownMenu)
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
        binding.dropdownMenu.setOnClickListener {
            popupMenu.show()
        }
    }

    private fun setToolBarMenuButton(){
        binding.menu.setOnClickListener{
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }
}