package com.ysdemo.mykotlin.ui.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutManager

/**
 * 禁止滑动的RecycleView布局管理器
 * Created by Song on 2017/2/21.
 */

class NoScrollLinearManager(context: Context, orientation: Int, reverseLayout: Boolean) : LinearLayoutManager(context, orientation, reverseLayout) {

    override fun canScrollVertically(): Boolean {
        return false
    }

    override fun canScrollHorizontally(): Boolean {
        return false
    }
}
