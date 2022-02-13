package com.mgstudio.phonehelper.app.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HttpRequestInterceptor @Inject constructor() : Interceptor {

    companion object {
        const val AUTHORIZATION = "Authorization"
        const val BEARER = "Bearer"
        private const val ACCEPT_LANGUAGE = "Accept-Language"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
        requestBuilder.addHeader("x-rapidapi-host", "covid-193.p.rapidapi.com")
        requestBuilder.addHeader("x-rapidapi-key", "9868ceb04emshb4944df4bd82ac2p15eba8jsnb53b7091c82a")
        val request = requestBuilder.url(originalRequest.url).build()
        return chain.proceed(request)
    }
}