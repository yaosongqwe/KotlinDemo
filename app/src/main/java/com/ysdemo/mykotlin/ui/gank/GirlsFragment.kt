package com.ysdemo.mykotlin.ui.gank

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ysdemo.mykotlin.R
import com.ysdemo.mykotlin.ui.gank.model.GankItem
import com.ysdemo.mykotlin.ui.gank.model.GankNormalItem
import com.ysdemo.mykotlin.ui.widget.HeaderViewRecyclerAdapter
import com.ysdemo.mykotlin.ui.widget.LoadMoreView
import kotlinx.android.synthetic.main.fragment_refresh_recycler.*

/**
 * Demo for GANK
 * Created by Song on 2017/10/12.
 */
class GirlsFragment constructor() : Fragment(), IGank.View, SwipeRefreshLayout.OnRefreshListener {

    lateinit var gankPresenter: IGank.Presenter

    private lateinit var mAdapter: GirlsAdapter

    private lateinit var vLoadMore: LoadMoreView

    private lateinit var mLayoutManager: GridLayoutManager

    private var loadingMore = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_refresh_recycler, container, false)
        vLoadMore = inflater.inflate(R.layout.load_more, recycler_view, false) as LoadMoreView
        return rootView
    }

    private fun initView(view: View) {
        setPresenter(GankPresenter(this))
        refresh_layout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent)
        refresh_layout.setOnRefreshListener(this)
        mLayoutManager = GridLayoutManager(activity, 2)
        recycler_view.layoutManager = mLayoutManager
        recycler_view.setHasFixedSize(true)
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val reachBottom = mLayoutManager.findLastCompletelyVisibleItemPosition() >= mLayoutManager.itemCount - 1
                if (!loadingMore && reachBottom) {
                    loadingMore = true
                    loadMore()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val reachBottom = mLayoutManager.findLastCompletelyVisibleItemPosition() >= mLayoutManager.itemCount - 1
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !loadingMore && reachBottom) {
                    loadingMore = true
                    loadMore()
                }
            }
        })
        mAdapter = GirlsAdapter(this)
        mAdapter.onItemClickListener = object : GirlsAdapter.OnItemClickListener {
            override fun onClickItem(view: View, item: GankNormalItem) {
                startActivity(PictureActivity.newIntent(activity, item.page, item.gank._id))
            }
        }
        val adapter = HeaderViewRecyclerAdapter(mAdapter)
        adapter.loadingView = vLoadMore
        recycler_view.adapter = adapter
        refresh_layout.post {
            refresh_layout.isRefreshing = true
            refreshData()
        }

    }

    private fun loadMore() {
        vLoadMore.status = LoadMoreView.STATUS_LOADING
        gankPresenter.getGirls(mAdapter.curPage + 1)
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
        loadingMore = false
        vLoadMore.status = LoadMoreView.STATUS_FAIL
    }

    override fun loadingSuccess(data: List<GankItem>) {
    }

    override fun loadingNormalSuccess(data: List<GankNormalItem>, page: Int) {
        mAdapter.updateData(page, data)
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
        fun newInstance(): GirlsFragment {
            return GirlsFragment()
        }
    }
}
