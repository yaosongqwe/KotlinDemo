package com.ysdemo.mykotlin.net.data.entity

import java.util.Date;
import java.util.Objects;

/**
 * 干货数据
 *
 * 示例:
 * {
 * "_id": "5715097267765974f5e27db0",
 * "createdAt": "2016-04-19T00:21:06.420Z",
 * "desc": "\u6c34\u5e73\u8fdb\u5ea6\u6761",
 * "publishedAt": "2016-04-19T12:13:58.869Z",
 * "source": "chrome",
 * "type": "Android",
 * "url": "https://github.com/MasayukiSuda/AnimateHorizontalProgressBar",
 * "used": true,
 * "who": "Jason"
 * }
 */
data class Gank(
        var _id: String = "",
        var createdAt: Date = Date(),
        var desc: String = "",
        var publishedAt: Date = Date(),
        var source: String = "",
        var type: String = "",
        var url: String = "",
        var used: Boolean = false,
        var who: String = ""
)
