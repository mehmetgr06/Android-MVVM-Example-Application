package com.mgstudio.phonehelper.app.base

import android.content.Context
import android.content.SharedPreferences
import com.mgstudio.phonehelper.R
import javax.inject.Singleton

/**
 * General Preferences Helper class, used for storing preference values using the Preference API
 */
@Singleton
open class PreferencesHelper(private val context: Context) {

    companion object {
        private const val PREF_PACKAGE_NAME = "com.mgstudio.phonehelper"
        private const val PREF_USER_NAME = "user_name"
    }

    private val pref: SharedPreferences =
        context.getSharedPreferences(PREF_PACKAGE_NAME, Context.MODE_PRIVATE)

    var userName: String?
        get() = pref.getString(PREF_USER_NAME, context.getString(R.string.title_username))
        set(userName) = pref.edit().putString(PREF_USER_NAME, userName).apply()

}