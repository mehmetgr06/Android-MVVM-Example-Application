package com.mgstudio.phonehelper.app.ui.profile

import android.app.AppOpsManager
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Process.myUid
import android.provider.Settings
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.mgstudio.phonehelper.R
import com.mgstudio.phonehelper.app.base.BaseFragment
import com.mgstudio.phonehelper.app.base.MagicRecyclerAdapter
import com.mgstudio.phonehelper.app.base.ProfileItemClickListener
import com.mgstudio.phonehelper.app.extension.showBasicDialog
import com.mgstudio.phonehelper.app.extension.showDialog
import com.mgstudio.phonehelper.app.extension.showToast
import com.mgstudio.phonehelper.app.model.base.TimeStamps.START_HOUR_OF_DAY
import com.mgstudio.phonehelper.app.model.base.TimeStamps.START_MINUTE_OF_DAY
import com.mgstudio.phonehelper.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>(),
    ProfileItemClickListener {

    private val profileViewModel: ProfileViewModel by viewModels()

    private val profileInfoAdapter = MagicRecyclerAdapter<ProfileItemClickListener, String>(
        R.layout.item_profile_info, this
    )
    private val profileUsageAdapter = MagicRecyclerAdapter<Any, String>(
        R.layout.item_profile_usage
    )

    private val profileAdapter = ConcatAdapter(
        profileInfoAdapter,
        profileUsageAdapter
    )

    override fun getLayoutId(): Int = R.layout.fragment_profile

    override fun getViewModel(): ProfileViewModel = profileViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isUsageStatsPermissionGranted()) {
            showCurrentDayUsage()
        } else {
            showPermissionDialog()
        }

        setProfileData()
        binding.rvProfile.adapter = profileAdapter
    }

    override fun onResume() {
        super.onResume()
        if (isUsageStatsPermissionGranted()) {
            showCurrentDayUsage()
        }
    }


    private fun setProfileData() {
        profileInfoAdapter.setData(
            profileViewModel.getUserName() ?: getString(R.string.title_username)
        )
    }

    private fun showCurrentDayUsage() {
        val time = Calendar.getInstance()
        time.set(Calendar.HOUR_OF_DAY, START_HOUR_OF_DAY)
        time.set(Calendar.MINUTE, START_MINUTE_OF_DAY)

        val startTime = time.timeInMillis
        val currentTime = System.currentTimeMillis()

        val usageStatsManager: UsageStatsManager =
            requireContext().getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val stats = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            startTime,
            currentTime
        )

        var minutes: Long = 0
        var hours: Long = 0

        stats.forEach { value ->
            val hour = TimeUnit.MILLISECONDS.toHours(value.totalTimeInForeground).toInt() % 24
            val minute = TimeUnit.MILLISECONDS.toMinutes(value.totalTimeInForeground).toInt() % 60
            minutes += minute
            hours += hour
        }
        profileUsageAdapter.setData("YOU SPENT $hours hours $minutes mins")
    }


    override fun onEditClick() {
        UsernameEditDialog(requireContext()) { userName ->
            if (userName.isNotBlank()) {
                profileViewModel.setUserName(userName)
                setProfileData()
            } else {
                requireContext().showToast(getString(R.string.text_warning_username_empty))
            }
        }.create().show()
    }

    override fun onProfileImageClick() {
        TODO("Not yet implemented")
    }

    private fun isUsageStatsPermissionGranted(): Boolean {
        val appOps = requireContext().getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            appOps.unsafeCheckOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                myUid(),
                requireContext().packageName
            )
        } else {
            appOps.checkOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                myUid(),
                requireContext().packageName
            )
        }
        return mode == AppOpsManager.MODE_ALLOWED
    }

    private fun showPermissionDialog() {
        requireContext().showDialog(
            getString(R.string.title_information),
            getString(R.string.text_usage_permission),
            getString(R.string.title_grant_permission),
            getString(R.string.title_cancel),
            topButtonClick = {
                startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
            },
            bottomButtonClick = {
                requireContext().showBasicDialog(
                    getString(R.string.text_usage_permission_warning)
                )
            }
        )
    }
}