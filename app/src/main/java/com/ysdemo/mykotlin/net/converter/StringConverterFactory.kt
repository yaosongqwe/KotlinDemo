package com.ysdemo.mykotlin.net.converter

import retrofit2.Retrofit
import okhttp3.ResponseBody
import retrofit2.Converter
import java.lang.reflect.Type


/**
 * Demo for Factory
 * Created by Song on 2017/10/11.
 */
class StringConverterFactory : Converter.Factory() {

    // 我们只关实现从ResponseBody 到 String 的转换，所以其它方法可不覆盖
    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
        if (type === String::class.java) {
            return StringConverter
        }
        //其它类型我们不处理，返回null就行
        return null
    }

    companion object {

        val INSTANCE = StringConverterFactory()

        fun create(): StringConverterFactory {
            return INSTANCE
        }
    }
}
