package com.ysdemo.mykotlin.ui.anko

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.ysdemo.mykotlin.R
import com.ysdemo.mykotlin.ui.base.ViewBinder
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Demo for ankoLayout
 * Created by Song on 2017/10/16.
 */
class AnkoLayout : ViewBinder<AnkoActivity> {

    private val customStyle = { v: Any ->
        when (v) {
            is Button -> {
                v.setAllCaps(false)
                v.textSize = 14f
                v.textColor = R.color.textColorPrimary
            }
        }
    }

    override fun bind(ankoActivity: AnkoActivity): View =
            ankoActivity.UI {
                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    padding = dip(30)
                    scrollView {
                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            imageView {
                                setImageResource(R.mipmap.a1)
                            }.lparams {
                                width = wrapContent
                                height = wrapContent
                            }
                            verticalLayout {
                                button("toast") {
                                    onClick {
                                        ankoActivity.toast("toast by Anko")
                                    }
                                }
                                button("intent") {
                                    onClick {
                                        ankoActivity.startActivity<AnkoActivity>()
                                    }
                                }
                                button("call") {
                                    onClick {
                                        makeCall("15371056697")
                                    }
                                }
                                button("browse") {
                                    onClick {
                                        browse("http://m.baidu.com")
                                    }
                                }
                                button("share") {
                                    onClick {
                                        share("anko", "anko123")
                                    }
                                }
                                button("dialog") {
                                    onClick {
                                        alert("MyKotlin") {
                                            customTitle {
                                                verticalLayout {
                                                    //用到了anko-layout库
                                                    padding = dip(10)
                                                    imageView(R.mipmap.ic_launcher)//方便的设置内容
                                                    editText {
                                                        hint = "hint_title"
                                                    }.lparams { margin = dip(10) }
                                                }
                                            }
                                            positiveButton("好的") { toast("button-ok") }
                                            negativeButton("取消") { }
                                        }.show()
                                    }
                                }
                                button("list") {
                                    onClick {
                                        val countries = listOf("Russia", "USA", "England", "Australia")
                                        selector("Where are you from?", countries) { ds, i ->
                                            toast("So you're living in ${countries[i]}, right?")
                                        }
                                    }
                                }
                            }.lparams {
                                width = matchParent
                                height = matchParent
                                weight = 1f
                            }
                        }
                    }.lparams {
                        width = matchParent
                        height = matchParent
                    }
                }.applyRecursively(customStyle)
            }.view

    override fun unbind(ankoActivity: AnkoActivity) {
    }
}