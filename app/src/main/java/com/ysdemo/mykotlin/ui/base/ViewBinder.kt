package com.ysdemo.mykotlin.ui.base

import android.view.View

/**
 * Demo for vlayout
 * Created by Song on 2017/10/16.
 */
interface ViewBinder<in T> {
    fun bind(t: T) : View
    fun unbind(t: T)
}