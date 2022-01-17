package com.mgstudio.phonehelper.app.model.base

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("error")
    val error: Boolean = false,
    @SerializedName("message")
    val message: String = "",
    @SerializedName("errorCode")
    val errorCode: Int = -1,
    @SerializedName("statusCode")
    val statusCode: Int = -1,
    @SerializedName("result")
    val result: T? = null
)