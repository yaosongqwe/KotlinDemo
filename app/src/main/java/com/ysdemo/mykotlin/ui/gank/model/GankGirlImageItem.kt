package com.ysdemo.mykotlin.ui.gank.model

/*
 * Copyright (C) 2016 Johnny Shieh Open Source Project
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


import com.ysdemo.mykotlin.net.data.entity.Gank
import java.util.Date

/**
 * description

 * @author Johnny Shieh (JohnnyShieh17@gmail.com)
 * *
 * @version 1.0
 */
data class GankGirlImageItem(var imgUrl: String = "", var publishedAt: Date = Date()) : GankItem {

    companion object {
        @JvmStatic fun newImageItem(gank: Gank): GankGirlImageItem {
            return GankGirlImageItem(gank.url, gank.publishedAt)
        }
    }
}
