package com.example.phonehelper.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.example.phonehelper.R
import com.example.phonehelper.app.base.BaseActivity
import com.example.phonehelper.app.ui.profile.ProfileViewModel
import com.example.phonehelper.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, ProfileViewModel>() {

    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): ProfileViewModel = profileViewModel


}