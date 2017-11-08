package com.ysdemo.mykotlin.ui.gank.model

import com.ysdemo.mykotlin.net.data.entity.Gank

data class GankNormalItem(var page: Int = -1, var gank: Gank = Gank()) : GankItem {

    companion object {
        @JvmStatic
        fun newGankList(gankList: List<Gank>?): List<GankNormalItem> {
            if (null == gankList || gankList.isEmpty()) {
                return arrayListOf()
            }
            return gankList.map { GankNormalItem(gank = it) }
        }

        @JvmStatic
        fun newGankList(gankList: List<Gank>?, pageIndex: Int): List<GankNormalItem> {
            if (null == gankList || gankList.isEmpty()) {
                return arrayListOf()
            }
            return gankList.map { GankNormalItem(page = pageIndex, gank = it) }
        }
    }

}
