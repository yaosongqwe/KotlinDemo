@file:JvmName("DayData")
package com.ysdemo.mykotlin.net.data.response
data class DayData(
        var category: MutableList<String> = mutableListOf(),
        var results: Result = Result()
)