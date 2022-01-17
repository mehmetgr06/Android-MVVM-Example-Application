package com.mgstudio.phonehelper.app.model.profile

data class ProfileUsageStat(
    var currentDayUsageHour: Int? = 0,
    var currentDayUsageMinute: Int = 0,
    var dailyAverageUsage: String? = "",
    var weeklyAverageUsage: String? = "",
    var weekdaysAverageUsage: String? = "",
    var weekendsAverageUsage: String? = "",
)