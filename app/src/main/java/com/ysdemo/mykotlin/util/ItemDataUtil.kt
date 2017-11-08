package com.ysdemo.mykotlin.util

import com.ysdemo.mykotlin.ui.baseModel.ArrayItem

/**
 * Demo for vlayout
 * Created by Song on 2017/10/10.
 */
class ItemDataUtil {
    companion object {
        fun getMainMenu(): ArrayList<ArrayItem> {
            val mainMenuList = ArrayList<ArrayItem>()
            mainMenuList.add(ArrayItem("1","card.io"))
            mainMenuList.add(ArrayItem("2","vlayout"))
            mainMenuList.add(ArrayItem("3","gank"))
            mainMenuList.add(ArrayItem("4","anko"))
            return mainMenuList
        }
        fun getGankMenu(): ArrayList<ArrayItem> {
            val mainMenuList = ArrayList<ArrayItem>()
            mainMenuList.add(ArrayItem("1","今日"))
            mainMenuList.add(ArrayItem("2","福利"))
            mainMenuList.add(ArrayItem("3","搜搜看"))
            return mainMenuList
        }
    }
}