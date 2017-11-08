package com.wizarpos.retail.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ysdemo.mykotlin.R
import com.ysdemo.mykotlin.ui.baseModel.ArrayItem
import com.ysdemo.mykotlin.util.ItemDataUtil

/**
 * 通用数据Adapter
 * Created by Song on 2017年7月26日.
 */

class MenuItemAdapter : BaseQuickAdapter<ArrayItem, BaseViewHolder>(R.layout.item_data, ItemDataUtil.getMainMenu()) {

    override fun convert(helper: BaseViewHolder, item: ArrayItem?) {
        if (null != item) {
            helper.setText(R.id.tvName, item.showValue)
        }
    }

}
