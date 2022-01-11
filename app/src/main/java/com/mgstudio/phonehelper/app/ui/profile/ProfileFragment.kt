package com.mgstudio.phonehelper.app.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.mgstudio.phonehelper.R
import com.mgstudio.phonehelper.app.base.BaseFragment
import com.mgstudio.phonehelper.app.base.MagicRecyclerAdapter
import com.mgstudio.phonehelper.app.base.ProfileItemClickListener
import com.mgstudio.phonehelper.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>(),
    ProfileItemClickListener {

    private val profileViewModel: ProfileViewModel by viewModels()

    private val profileInfoAdapter = MagicRecyclerAdapter<ProfileItemClickListener, String>(
        R.layout.item_profile_info, this
    )

    private val profileAdapter = ConcatAdapter(profileInfoAdapter)

    override fun getLayoutId(): Int = R.layout.fragment_profile

    override fun getViewModel(): ProfileViewModel = profileViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileInfoAdapter.setData("dsasad")
        binding.rvProfile.adapter = profileAdapter
    }


    override fun onEditClick() {
        UsernameEditDialog(requireContext()) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }.create().show()
    }

    override fun onProfileImageClick() {
        TODO("Not yet implemented")
    }


}