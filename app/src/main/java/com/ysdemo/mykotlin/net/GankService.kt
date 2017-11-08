package com.ysdemo.mykotlin.net

import com.ysdemo.mykotlin.net.data.response.DateData
import com.ysdemo.mykotlin.net.data.response.DayData
import com.ysdemo.mykotlin.net.data.response.GankData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Demo for Retrofit2
 * Created by Song on 2017/10/10.
 */
interface GankService {

    @GET(GankApi.DATE_HISTORY)
    fun getDateHistory(): Observable<DateData>

    @GET("data/{category}/{pageCount}/{page}")
    fun getGank(@Path("category") category: String, @Path("pageCount") pageCount: Int, @Path("page") page: Int): Observable<GankData>

    @GET("day/{year}/{month}/{day}")
    fun getDayGank(@Path("year") year: Int, @Path("month") month: Int, @Path("day") day: Int): Observable<DayData>

    @GET("search/query/{queryText}/category/all/count/{count}/page/{page}")
    fun queryGank(@Path("queryText") queryText: String, @Path("count") count: Int, @Path("page") page: Int): Observable<GankData>

}