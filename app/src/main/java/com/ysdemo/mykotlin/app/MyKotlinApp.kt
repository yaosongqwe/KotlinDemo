package com.ysdemo.mykotlin.app

import android.app.Application
import com.ysdemo.mykotlin.net.NetRequest

/**
 * Created by Song on 2017/8/18.
 */
class MyKotlinApp : Application() {

    companion object {
        //标准委托，若在onCreate前调用此实例会直接抛出异常 IllegalStateException
//        var instance: MyKotlinApp by Delegates.notNull()
        //或者 这种方式不能处理基础数据类型 int， float ，double等
        @JvmStatic
        lateinit var instance: MyKotlinApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initNet()
    }

    private fun initNet() {
        NetRequest.init()
    }

}