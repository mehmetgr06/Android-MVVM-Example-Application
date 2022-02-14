package com.mgstudio.phonehelper.app.ui.reports

import androidx.fragment.app.activityViewModels
import com.mgstudio.phonehelper.R
import com.mgstudio.phonehelper.app.base.BaseFragment
import com.mgstudio.phonehelper.app.ui.profile.ProfileViewModel
import com.mgstudio.phonehelper.databinding.FragmentReportsBinding

class ReportsFragment: BaseFragment<FragmentReportsBinding, ProfileViewModel>() {

    private val profileViewModel: ProfileViewModel by activityViewModels()

    override fun getLayoutId(): Int = R.layout.fragment_reports

    override fun getViewModel(): ProfileViewModel = profileViewModel

}