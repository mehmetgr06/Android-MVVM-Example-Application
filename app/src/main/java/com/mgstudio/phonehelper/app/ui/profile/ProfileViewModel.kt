package com.mgstudio.phonehelper.app.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mgstudio.phonehelper.app.base.BaseViewModel
import com.mgstudio.phonehelper.app.base.PreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val preferencesHelper: PreferencesHelper) :
    BaseViewModel() {

    fun setUserName(userName: String) {
        preferencesHelper.userName = userName
    }

    fun getUserName() = preferencesHelper.userName

}