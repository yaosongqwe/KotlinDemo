package com.ysdemo.mykotlin.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * JustDemo
 * 继承BroadcastReceiver的一个单例
 * Created by Song on 2017/9/1.
 */
object DemoSingle : BroadcastReceiver(){
    //重写父类方法
    override fun onReceive(p0: Context?, p1: Intent?) {

    }

    //定义新方法
    fun doSomeThing(p0 : String) {
        Log.d("single", p0)
    }
}