package com.ysdemo.mykotlin.net.data.response

import com.ysdemo.mykotlin.net.data.entity.Gank

data class GankData(var results: MutableList<Gank> = mutableListOf()) : BaseData()
