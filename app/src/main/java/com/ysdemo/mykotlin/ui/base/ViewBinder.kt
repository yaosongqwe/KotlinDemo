package com.ysdemo.mykotlin.ui.base

import android.view.View
import org.jetbrains.anko.AnkoComponent

/**
 * Demo for vlayout
 * Created by Song on 2017/10/16.
 */
interface ViewBinder<in T> : AnkoComponent<T> {
    fun unbind(t: T)
}