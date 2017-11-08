package com.ysdemo.mykotlin.ui.gank

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.ysdemo.mykotlin.R
import com.ysdemo.mykotlin.ui.baseModel.ArrayItem
import com.ysdemo.mykotlin.ui.widget.NoScrollGridManager
import kotlinx.android.synthetic.main.activity_gank.*

/**
 * Demo for gank
 * Created by Song on 2017/10/10.
 */
class GankActivity : AppCompatActivity() {
    companion object {
        private val FRAGMENT_TODAY = "TODAY"
        private val FRAGMENT_GIRLS = "GIRLS"
        private val tagList = arrayOf<String>(FRAGMENT_TODAY, FRAGMENT_GIRLS)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gank)
        title = "Gank Demo"
        rvGankMenu.apply {
            layoutManager = NoScrollGridManager(this@GankActivity, 3, GridLayoutManager.VERTICAL, false)
            adapter = GankMenuAdapter().apply {
                onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                    doMenuAction(adapter.getItem(position) as ArrayItem)
                    (adapter as GankMenuAdapter).selectedItem = adapter.getItem(position) as ArrayItem
                    adapter.notifyDataSetChanged()
                }
            }
        }
        showToday()
    }

    private fun doMenuAction(item: ArrayItem) {
        when (item.realValue) {
            "1" -> {
                showToday()
            }
            "2" -> {
                showGirls()
            }
            "3" -> {
                //TODO 搜索
            }
        }
    }

    private fun showToday() {
        hideOther(FRAGMENT_TODAY)
        var todayFragment: Fragment? = supportFragmentManager.findFragmentByTag(FRAGMENT_TODAY)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (todayFragment == null || !todayFragment.isAdded()) {
            todayFragment = TodayFragment.newInstance()
            fragmentTransaction.add(R.id.flGank, todayFragment, FRAGMENT_TODAY)
        }
        fragmentTransaction.show(todayFragment).commit()
    }

    private fun showGirls() {
        hideOther(FRAGMENT_GIRLS)
        var girlFragment: Fragment? = supportFragmentManager.findFragmentByTag(FRAGMENT_GIRLS)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (girlFragment == null || !girlFragment.isAdded()) {
            girlFragment = GirlsFragment.newInstance()
            fragmentTransaction.add(R.id.flGank, girlFragment, FRAGMENT_GIRLS)
        }
        fragmentTransaction.show(girlFragment).commit()
    }

    /**
     * 隐藏其他fragment

     * @param fragment
     */
    private fun hideOther(fragment: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var needCommit = false
        tagList.forEach {
            if (!TextUtils.equals(fragment, it)) {
                val hideFragment = supportFragmentManager.findFragmentByTag(it)
                if (null != hideFragment) {
                    fragmentTransaction.hide(hideFragment)
                    needCommit = true
                }
            }
        }
        if (needCommit) {
            fragmentTransaction.commitAllowingStateLoss()
        }
    }
}
