package com.ysdemo.mykotlin.net.converter

import okhttp3.ResponseBody
import retrofit2.Converter

/**
 * Demo for Converter
 * Created by Song on 2017/10/11.
 */
object StringConverter : Converter<ResponseBody, String> {
    override fun convert(value: ResponseBody): String {
        return value.string()
    }
}