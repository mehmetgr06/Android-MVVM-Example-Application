package com.mgstudio.phonehelper.app.model.feed

import com.google.gson.annotations.SerializedName

data class FeedResponse (
    val continent: String,
    val country: String,
    val population: Long,
    val cases: Cases,
    val deaths: Deaths,
    val tests: Tests,
    val day: String,
    val time: String
)

data class Cases (
    val new: String,
    val active: Long,
    val critical: Long,
    val recovered: Long,
    @SerializedName("1M_pop")
    val the1MPop: String,
    val total: Long
)

data class Deaths (
    val new: String?,
    @SerializedName("1M_pop")
    val the1MPop: String,
    val total: Long
)

data class Tests (
    @SerializedName("1M_pop")
    val the1MPop: String,
    val total: Long
)