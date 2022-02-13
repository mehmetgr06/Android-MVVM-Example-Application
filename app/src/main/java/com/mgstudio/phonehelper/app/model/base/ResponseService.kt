package com.mgstudio.phonehelper.app.model.base

import com.google.gson.annotations.SerializedName
import com.mgstudio.phonehelper.app.model.feed.FeedResponse

data class ResponseService <T>(
    @SerializedName("get")
    val get: String?,
    @SerializedName("parameters")
    val parameters: Any?,
    @SerializedName("errors")
    val errors: MutableList<Any>?,
    @SerializedName("results")
    val results: Any?,
    @SerializedName("response")
    val response: MutableList<FeedResponse>?
)

