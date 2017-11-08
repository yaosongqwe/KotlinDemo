package com.ysdemo.mykotlin.ui.gank

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ysdemo.mykotlin.ui.gank.model.GankGirlImageItem
import com.ysdemo.mykotlin.ui.gank.model.GankItem
import com.ysdemo.mykotlin.ui.gank.model.GankNormalItem
import com.ysdemo.mykotlin.R
import kotlinx.android.synthetic.main.fragment_refresh_recycler.*

/**
 * Demo for GANK
 * Created by Song on 2017/10/11.
 */
class TodayFragment : Fragment(), IGank.View, SwipeRefreshLayout.OnRefreshListener {

    lateinit var gankPresenter: IGank.Presenter

    private lateinit var mAdapter: GankListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_refresh_recycler, container, false)
        return rootView
    }

    private fun initView(view: View) {
        setPresenter(GankPresenter(this))
        refresh_layout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent)
        refresh_layout.setOnRefreshListener(this)
        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.setHasFixedSize(true)
        mAdapter = GankListAdapter(this)
        mAdapter.onItemClickListener = object : GankListAdapter.OnItemClickListener {
            override fun onClickNormalItem(view: View, normalItem: GankNormalItem) {
                if (!normalItem.gank.url.isNullOrEmpty()) {
                    WebviewActivity.openUrl(activity, normalItem.gank.url, normalItem.gank.desc)
                }
            }

            override fun onClickGirlItem(view: View, girlImageItem: GankGirlImageItem) {
                if (!girlImageItem.imgUrl.isNullOrEmpty()) {
                    startActivity(PictureActivity.newIntent(activity, girlImageItem.imgUrl, girlImageItem.publishedAt))
                }
            }
        }
        recycler_view.adapter = mAdapter
        refresh_layout.post {
            refresh_layout.isRefreshing = true
            refreshData()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun refreshData() {
        gankPresenter.getToday()
    }

    override fun onRefresh() {
        refreshData()
    }

    override fun startLoading() {
        refresh_layout.isRefreshing = true
    }
    override fun endLoading() {
        refresh_layout.isRefreshing = false
    }

    override fun loadingSuccess(data: List<GankItem>) {
        mAdapter.swapData(data)
    }

    override fun loadingNormalSuccess(data: List<GankNormalItem>, page: Int) {
    }

    override fun loadingFailed() {
        Toast.makeText(activity, "加载失败", Toast.LENGTH_SHORT).show()
    }

    override fun setPresenter(presenter: IGank.Presenter) {
        gankPresenter = presenter
        gankPresenter.subscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
        gankPresenter.unsubscribe()
    }

    companion object {
        @JvmStatic
        fun newInstance(): TodayFragment {
            return TodayFragment()
        }
    }
}
