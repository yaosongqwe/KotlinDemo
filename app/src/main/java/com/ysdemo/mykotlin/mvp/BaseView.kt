package com.ysdemo.mykotlin.mvp

/**
 * Base view in MVP architecture.
 */

interface BaseView<in T> {

    fun setPresenter(presenter : T)

}