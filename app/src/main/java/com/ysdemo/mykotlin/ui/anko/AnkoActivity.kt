package com.ysdemo.mykotlin.ui.anko

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.ViewGroup
import org.jetbrains.anko.*

/**
 * Demo for anko
 * Created by Song on 2017/10/16.
 */
class AnkoActivity :AppCompatActivity() {

    private var toolBar: Toolbar? = null
    private var container: ViewGroup? = null

    private var layout : AnkoLayout = AnkoLayout()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout.setContentView(this)
    }
}