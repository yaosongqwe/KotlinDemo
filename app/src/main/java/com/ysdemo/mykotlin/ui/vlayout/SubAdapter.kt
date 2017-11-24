package com.ysdemo.mykotlin.ui.vlayout

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.ysdemo.mykotlin.R

open class SubAdapter @JvmOverloads constructor(private val mContext: Context, private val mLayoutHelper: LayoutHelper, count: Int, private val mLayoutParams: VirtualLayoutManager.LayoutParams = VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300)) : DelegateAdapter.Adapter<DemoViewHolder>() {
    private var mCount = 0

    init {
        this.mCount = count
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return mLayoutHelper
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
        return DemoViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        // only vertical
        holder.itemView.layoutParams = VirtualLayoutManager.LayoutParams(mLayoutParams)
    }


    override fun onBindViewHolderWithOffset(holder: DemoViewHolder?, position: Int, offsetTotal: Int) {
        (holder?.itemView?.findViewById(R.id.title) as? TextView)?.text = Integer.toString(offsetTotal)
    }

    override fun getItemCount(): Int {
        return mCount
    }
}
