package com.mgstudio.phonehelper.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.mgstudio.phonehelper.R
import com.mgstudio.phonehelper.app.base.BaseActivity
import com.mgstudio.phonehelper.app.ui.profile.ProfileViewModel
import com.mgstudio.phonehelper.databinding.ActivityMainBinding
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