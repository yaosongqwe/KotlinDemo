package com.ysdemo.mykotlin.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.wizarpos.retail.view.adapter.MenuItemAdapter
import com.ysdemo.mykotlin.R
import com.ysdemo.mykotlin.ui.anko.AnkoActivity
import com.ysdemo.mykotlin.ui.baseModel.ArrayItem
import com.ysdemo.mykotlin.ui.gank.GankActivity
import com.ysdemo.mykotlin.ui.vlayout.DemoActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvDemo.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = MenuItemAdapter().apply {
                onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                    doMenuAction(adapter.getItem(position) as ArrayItem)
                }
            }
        }
    }

    private fun doMenuAction(item: ArrayItem) {
        when (item.realValue) {
//            "1" -> {
//                val intent = Intent(this, CardActivity::class.java)
//                startActivity(intent)
//            }
            "2" -> {
                startActivity(Intent(this, DemoActivity::class.java))
            }
            "3" -> {
                startActivity(Intent(this, GankActivity::class.java))
            }
            "4" -> {
                startActivity<AnkoActivity>()
            }
        }
    }
}
