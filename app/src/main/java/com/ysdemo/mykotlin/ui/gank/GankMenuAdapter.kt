package com.ysdemo.mykotlin.ui.gank

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ysdemo.mykotlin.R
import com.ysdemo.mykotlin.ui.baseModel.ArrayItem
import com.ysdemo.mykotlin.util.ItemDataUtil

/**
 * 通用数据Adapter
 * Created by Song on 2017年7月26日.
 */

class GankMenuAdapter : BaseQuickAdapter<ArrayItem, BaseViewHolder>(R.layout.item_data, ItemDataUtil.getGankMenu()) {

    lateinit var selectedItem: ArrayItem

    init {
        if (mData.size > 0){
            selectedItem = mData[0]
        }
    }

    override fun convert(helper: BaseViewHolder, item: ArrayItem?) {
        if (null != item) {
            helper.setText(R.id.tvName, item.showValue)
        }
        helper.getView<View>(R.id.tvName).isEnabled = selectedItem != item
    }

}
