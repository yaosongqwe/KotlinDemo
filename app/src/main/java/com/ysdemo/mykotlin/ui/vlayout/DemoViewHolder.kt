package com.ysdemo.mykotlin.ui.vlayout

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Song on 2017/9/21.
 */
class DemoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    init {
        createdTimes++
        existing++
    }

    @Throws(Throwable::class)
    protected fun finalize() {
        existing--
    }

    companion object {

        @Volatile
        var existing = 0
        var createdTimes = 0
    }
}
