package com.ysdemo.mykotlin.net.data.response

import com.google.gson.annotations.SerializedName
import com.ysdemo.mykotlin.net.data.entity.Gank
data class Result(
        @SerializedName("Android") var androidList: MutableList<Gank> = mutableListOf(),
        @SerializedName("iOS") var iosList: MutableList<Gank> = mutableListOf(),
        @SerializedName("福利") var welfareList: MutableList<Gank> = mutableListOf(),
        @SerializedName("拓展资源") var extraList: MutableList<Gank> = mutableListOf(),
        @SerializedName("前端") var frontEndList: MutableList<Gank> = mutableListOf(),
        @SerializedName("瞎推荐") var casualList: MutableList<Gank> = mutableListOf(),
        @SerializedName("App") var appList: MutableList<Gank> = mutableListOf(),
        @SerializedName("休息视频") var videoList: MutableList<Gank> = mutableListOf()
)