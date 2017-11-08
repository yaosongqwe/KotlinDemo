package com.ysdemo.mykotlin.ui.gank

import com.ysdemo.mykotlin.net.GankType
import com.ysdemo.mykotlin.ui.gank.model.GankGirlImageItem
import com.ysdemo.mykotlin.ui.gank.model.GankHeaderItem
import com.ysdemo.mykotlin.ui.gank.model.GankItem
import com.ysdemo.mykotlin.ui.gank.model.GankNormalItem
import com.ysdemo.mykotlin.net.NetRequest
import com.ysdemo.mykotlin.net.data.response.DayData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Demo for MVP
 * Created by Song on 2017/10/11.
 */
class GankPresenter(var view: IGank.View) : IGank.Presenter {
    override fun getGirls(page: Int) {
        getGankList(GankType.WELFARE, page)
    }

    override fun getToday() {
        view.startLoading()
        NetRequest.gankService.getDateHistory()
                .filter { it.results.isNotEmpty() }
                .map { (results) ->
                    val calendar = Calendar.getInstance(Locale.CHINA)
                    try {
                        calendar.time = sDataFormat.parse(results[0])
                    } catch (e: ParseException) {
                        e.printStackTrace()
                    }
                    calendar
                }
                .flatMap { calendar ->
                    NetRequest.gankService
                            .getDayGank(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
                }
                .map { getGankList(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ gankList ->
                    view.endLoading()
                    view.loadingSuccess(gankList)
                }, { throwable ->
                    view.endLoading()
                    view.loadingFailed()
                    throwable.printStackTrace()
                })
    }

    init {
        view.setPresenter(this)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
    }

    companion object {
        private val sDataFormat = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
    }

    private fun getGankList(dayData: DayData?): List<GankItem> {
        if (null == dayData) {
            return arrayListOf()
        }
        val gankList = ArrayList<GankItem>(10)
        if (dayData.results.welfareList.size > 0) {
            gankList.add(GankGirlImageItem.newImageItem(dayData.results.welfareList[0]))
        }
        if (dayData.results.androidList.isNotEmpty()) {
            gankList.add(GankHeaderItem(GankType.ANDROID))
            gankList.addAll(GankNormalItem.newGankList(dayData.results.androidList))

        }
        if (dayData.results.iosList.isNotEmpty()) {
            gankList.add(GankHeaderItem(GankType.IOS))
            gankList.addAll(GankNormalItem.newGankList(dayData.results.iosList))
        }
        if (dayData.results.frontEndList.isNotEmpty()) {
            gankList.add(GankHeaderItem(GankType.FRONTEND))
            gankList.addAll(GankNormalItem.newGankList(dayData.results.frontEndList))
        }
        if (dayData.results.extraList.isNotEmpty()) {
            gankList.add(GankHeaderItem(GankType.EXTRA))
            gankList.addAll(GankNormalItem.newGankList(dayData.results.extraList))
        }
        if (dayData.results.casualList.isNotEmpty()) {
            gankList.add(GankHeaderItem(GankType.CASUAL))
            gankList.addAll(GankNormalItem.newGankList(dayData.results.casualList))
        }
        if (dayData.results.appList.isNotEmpty()) {
            gankList.add(GankHeaderItem(GankType.APP))
            gankList.addAll(GankNormalItem.newGankList(dayData.results.appList))
        }
        if (dayData.results.videoList.isNotEmpty()) {
            gankList.add(GankHeaderItem(GankType.VIDEO))
            gankList.addAll(GankNormalItem.newGankList(dayData.results.videoList))
        }
        return gankList
    }


    protected fun getGankList(category: String, page: Int) {
        view.startLoading()
        NetRequest.gankService.getGank(category, page, page)
                .filter { it.results.isNotEmpty() }
                .map { GankNormalItem.newGankList(it.results, page) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ gankNormalItems ->
                    view.endLoading()
                    view.loadingNormalSuccess(gankNormalItems, page)
                }, { throwable ->
                    view.endLoading()
                    view.loadingFailed()
                    throwable.printStackTrace()
                })
    }
}