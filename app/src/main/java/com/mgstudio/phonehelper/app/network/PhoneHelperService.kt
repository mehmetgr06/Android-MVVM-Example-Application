package com.mgstudio.phonehelper.app.network

import com.mgstudio.phonehelper.app.model.base.ResponseService
import com.mgstudio.phonehelper.app.model.feed.FeedResponse
import retrofit2.http.GET

interface PhoneHelperService {

    @GET("history?country=usa&day=2020-06-02")
    suspend fun getHistory(): ResponseService<FeedResponse>

}