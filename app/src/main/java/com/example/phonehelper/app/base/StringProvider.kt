package com.example.phonehelper.app.base

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StringProvider @Inject constructor(@ApplicationContext private val context: Context) :
    StringProviderHelper {

}