package com.ysdemo.mykotlin.net

import com.google.gson.GsonBuilder
import com.johnny.gank.core.http.DateDeserializer
import com.ysdemo.mykotlin.BuildConfig
import com.ysdemo.mykotlin.net.converter.StringConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * Demo for OkHttp + Retrofit2
 * Created by Song on 2017/10/10.
 */
object NetRequest {
    private lateinit var url: String
    private lateinit var client: OkHttpClient
    lateinit var gankService: GankService

    @JvmField
    internal val DATE_PATTERN1 = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS"
    @JvmField
    internal val DATE_PATTERN2 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

    fun init() {
        url = GankApi.BASE_URL
        client = OkHttpClient.Builder().run {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            build()
        }
        val dateGson = GsonBuilder()
                .registerTypeAdapter(Date::class.java, DateDeserializer(DATE_PATTERN1, DATE_PATTERN2))
                .serializeNulls()
                .create()
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(dateGson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        gankService = retrofit.create(GankService::class.java)
    }

}