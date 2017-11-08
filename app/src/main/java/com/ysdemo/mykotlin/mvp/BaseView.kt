package io.github.tonnyl.mango.mvp

/**
 * Base view in MVP architecture.
 */

interface BaseView<in T> {

    fun setPresenter(presenter : T)

}