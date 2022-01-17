package com.mgstudio.phonehelper.app.di

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.provider.Settings
import androidx.recyclerview.widget.ConcatAdapter
import com.mgstudio.phonehelper.app.base.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideNavigator() = Navigator()

    @Provides
    @Singleton
    fun provideString(@ApplicationContext context: Context): StringProviderHelper = StringProvider(context)

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context) = context

    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context) = PreferencesHelper(context)

    @Provides
    @Singleton
    fun provideExposer() = Exposer()

    @Provides
    fun provideConcatAdapter() = ConcatAdapter()

    @Provides
    @Singleton
    fun provideConfiguration() = Configuration()

    @SuppressLint("HardwareIds")
    @Provides
    @Singleton
    fun provideAndroidId(@ApplicationContext context: Context) =
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

}