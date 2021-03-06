package com.ysdemo.mykotlin.ui.gank

import android.support.annotation.IntDef
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ysdemo.mykotlin.R
import com.ysdemo.mykotlin.ui.gank.model.GankNormalItem
import kotlinx.android.synthetic.main.pager_item_picture.view.*

class PicturePagerAdapter : PagerAdapter() {

    companion object {
        const val ADD_FRONT = -1L
        const val ADD_END = 1L
        const val ADD_NONE = 0L
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(ADD_FRONT, ADD_END, ADD_NONE)
    annotation class ADD_STATUS

    private val items = mutableListOf<GankNormalItem>()

    fun initList(list: List<GankNormalItem>) {
        items.addAll(list)
    }

    fun appendList(page: Int, list: List<GankNormalItem>): Long {
        if (0 == count) return ADD_NONE
        if (page == items[0].page - 1) {
            items.addAll(0, list)
            return ADD_FRONT
        } else if (page == items.last().page + 1) {
            items.addAll(items.size, list)
            return ADD_END
        }
        return ADD_NONE
    }

    fun getItem(position: Int): GankNormalItem? {
        if (position !in 0..(count - 1)) return null
        return items[position]
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.pager_item_picture, container, false)
        val item = items[position]
        Glide.with(container.context)
                .load(item.gank.url)
                .dontAnimate()
                .centerCrop()
                .into(view.pic)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any?) {
        if (`object` is View) container.removeView(`object`)
    }

    override fun getItemPosition(`object`: Any?): Int {
        return POSITION_NONE
    }
}
