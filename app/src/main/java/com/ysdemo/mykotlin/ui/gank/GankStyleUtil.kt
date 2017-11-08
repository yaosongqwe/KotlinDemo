package com.ysdemo.mykotlin.ui.gank

import android.support.v7.widget.RecyclerView
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.TextAppearanceSpan
import com.ysdemo.mykotlin.R
import com.ysdemo.mykotlin.app.MyKotlinApp

/**
 * Demo for vlayout
 * Created by Song on 2017/10/11.
 */
object GankStyleUtil {
    fun RecyclerView.Adapter<RecyclerView.ViewHolder>.getGankTitleStr(desc: String, who: String?): CharSequence {
        if (who.isNullOrEmpty()) return desc
        val builder = SpannableStringBuilder(desc)
        val spannableString = SpannableString(" ($who)")
        spannableString.setSpan(TextAppearanceSpan(MyKotlinApp.instance, R.style.SummaryTextAppearance), 0, spannableString.length, 0)
        builder.append(spannableString)
        return builder
    }

    fun RecyclerView.Adapter<RecyclerView.ViewHolder>.getGankTitleStr(desc: String, who: String?, type: String): CharSequence {
        if (who.isNullOrEmpty()) return desc
        val builder = SpannableStringBuilder(desc)
        val spannableWho = SpannableString(" ($who)")
        val spannableType = SpannableString(" ($type)")
        spannableWho.setSpan(TextAppearanceSpan(MyKotlinApp.instance, R.style.SummaryTextAppearance), 0, spannableWho.length, 0)
        spannableType.setSpan(TextAppearanceSpan(MyKotlinApp.instance, R.style.SummaryTextAppearance), 0, spannableType.length, 0)
        builder.append(spannableWho)
        builder.append(spannableType)
        return builder
    }
}