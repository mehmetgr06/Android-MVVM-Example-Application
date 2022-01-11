package com.mgstudio.phonehelper.app.base

import android.content.Context
import javax.inject.Singleton

/**
 * General Preferences Helper class, used for storing preference values using the Preference API
 */
@Singleton
open class PreferencesHelper(context: Context) {

    companion object {
        private const val PREF_PACKAGE_NAME = "com.marsathletic.android.macfit"
    }

}