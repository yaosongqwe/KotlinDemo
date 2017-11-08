package com.ysdemo.mykotlin.ui.gank.model

import com.ysdemo.mykotlin.net.data.entity.Gank

/*
 * Copyright (C) 2015 Johnny Shieh Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * description
 *
 * @author Johnny Shieh
 * @version 1.0
 */
 data class GankNormalItem(var page: Int = -1, var gank: Gank = Gank()) : GankItem {

    companion object {
        @JvmStatic
        fun newGankList(gankList: List<Gank>?): List<GankNormalItem> {
            if(null == gankList || gankList.isEmpty()) {
                return arrayListOf()
            }
            return gankList.map { GankNormalItem(gank = it) }
        }

        @JvmStatic
        fun newGankList(gankList: List<Gank>?, pageIndex: Int): List<GankNormalItem> {
            if(null == gankList || gankList.isEmpty()) {
                return arrayListOf()
            }
            return gankList.map { GankNormalItem(page = pageIndex, gank = it) }
        }
    }

}
