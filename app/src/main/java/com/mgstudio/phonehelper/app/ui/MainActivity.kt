package com.mgstudio.phonehelper.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mgstudio.phonehelper.R
import com.mgstudio.phonehelper.app.base.BaseActivity
import com.mgstudio.phonehelper.app.ui.profile.ProfileViewModel
import com.mgstudio.phonehelper.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, ProfileViewModel>() {

    private val profileViewModel: ProfileViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.apply {
            bottomNav.itemIconTintList = null
            bottomNav.setupWithNavController(navController)
        }

    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): ProfileViewModel = profileViewModel

}