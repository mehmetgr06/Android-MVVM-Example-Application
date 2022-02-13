package com.mgstudio.phonehelper.app.di

import com.google.gson.GsonBuilder
import com.mgstudio.phonehelper.BuildConfig
import com.mgstudio.phonehelper.app.model.base.DateFormats.SERVICE_DATE
import com.mgstudio.phonehelper.app.network.HttpRequestInterceptor
import com.mgstudio.phonehelper.app.network.PhoneHelperService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        okHttpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        OkHttpClient.Builder().apply {
            addInterceptor(HttpRequestInterceptor())
            addInterceptor(okHttpLoggingInterceptor)
            connectTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)

            return build()
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder().setDateFormat(SERVICE_DATE).create()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): PhoneHelperService {
        return retrofit.create(PhoneHelperService::class.java)
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }
}