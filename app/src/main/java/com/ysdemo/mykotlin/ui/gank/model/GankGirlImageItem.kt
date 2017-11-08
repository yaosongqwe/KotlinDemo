package com.ysdemo.mykotlin.ui.gank.model

import com.ysdemo.mykotlin.net.data.entity.Gank
import java.util.Date

data class GankGirlImageItem(var imgUrl: String = "", var publishedAt: Date = Date()) : GankItem {

    companion object {
        @JvmStatic fun newImageItem(gank: Gank): GankGirlImageItem {
            return GankGirlImageItem(gank.url, gank.publishedAt)
        }
    }
}
