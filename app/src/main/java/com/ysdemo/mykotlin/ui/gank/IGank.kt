package com.ysdemo.mykotlin.ui.gank

import com.ysdemo.mykotlin.ui.gank.model.GankItem
import com.ysdemo.mykotlin.ui.gank.model.GankNormalItem
import com.ysdemo.mykotlin.mvp.BasePresenter
import com.ysdemo.mykotlin.mvp.BaseView

/**
 * Demo for MVP
 * Created by Song on 2017/10/11.
 */
interface IGank {
    interface Presenter : BasePresenter {
        fun getToday()
        fun getGirls(page : Int)
    }

    interface View : BaseView<Presenter> {
        fun startLoading()
        fun endLoading()
        fun loadingSuccess(data: List<GankItem>)
        fun loadingNormalSuccess(data: List<GankNormalItem>, page: Int)
        fun loadingFailed()
    }
}