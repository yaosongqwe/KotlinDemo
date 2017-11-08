package com.ysdemo.mykotlin.ui.vlayout

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.ysdemo.mykotlin.R
import kotlinx.android.synthetic.main.activity_demo.*
import java.util.*


/**
 * Demo for vlayout
 * Created by Song on 2017/9/20.
 */
class DemoActivity : AppCompatActivity() {

    //轮播测试地址
    val SCREEN_IMAGE_URLS_DEMO = arrayOf("http://ww2.sinaimg.cn/large/b52da78ejw1eqjz89ms3jj21kw11x45b.jpg"
            , "http://imgsrc.baidu.com/forum/pic/item/0199c16eddc451da80f06b30b6fd5266d11632b6.jpg"
            , "http://img2.imgtn.bdimg.com/it/u=1072245499,1560849727&fm=21&gp=0.jpg"
            , "http://imgsrc.baidu.com/forum/pic/item/b151f8198618367a11d2084c2e738bd4b21ce5cc.jpg"
            , "http://imgsrc.baidu.com/forum/pic/item/2934349b033b5bb5fcfe34b236d3d539b600bc54.jpg"
            , "http://g.hiphotos.baidu.com/zhidao/pic/item/0b46f21fbe096b63781ed89e09338744eaf8acfd.jpg")
    val TAG = "DemoActivity"
    lateinit var layoutManager: VirtualLayoutManager
    lateinit var trigger: Runnable
    var isOpened = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        layoutManager = VirtualLayoutManager(this)
        rvDemo.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, scrollState: Int) {
            }

            override fun onScrolled(recyclerView: RecyclerView?, i: Int, i2: Int) {
                Log.d(TAG, "First: ${layoutManager.findFirstVisibleItemPosition()}")
                Log.d(TAG, "Existing: ${DemoViewHolder.existing} Created: ${DemoViewHolder.createdTimes}")
                Log.d(TAG, "Count: ${rvDemo.childCount}")
                Log.d(TAG, "Total Offset: ${layoutManager.offsetToStart}")
            }
        })
        layoutManager.setRecycleOffset(300)
        rvDemo.layoutManager = layoutManager

//        val itemDecoration = object : RecyclerView.ItemDecoration() {
//            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
////                val position = (view.layoutParams as VirtualLayoutManager.LayoutParams).viewPosition
//                outRect.set(4, 4, 4, 4)
//            }
//        }
//        rvDemo.addItemDecoration(itemDecoration)

        val viewPool = RecyclerView.RecycledViewPool()
        rvDemo.recycledViewPool = viewPool

        val delegateAdapter = DelegateAdapter(layoutManager, true)
        rvDemo.adapter = delegateAdapter
        val adapters = LinkedList<DelegateAdapter.Adapter<*>>()

        //Banner
        adapters.add(object : SubAdapter(this, LinearLayoutHelper(), 1) {

            override fun onViewRecycled(holder: DemoViewHolder?) {
                if (holder!!.itemView is Banner) {
//                    (holder.itemView as Banner).adapter = null
                }
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
                if (viewType == 1) {
                    return DemoViewHolder(
                            LayoutInflater.from(this@DemoActivity).inflate(R.layout.layout_banner, parent, false))
                }
                return super.onCreateViewHolder(parent, viewType)
            }

            override fun getItemViewType(position: Int): Int {
                return 1
            }

            override fun onBindViewHolderWithOffset(holder: DemoViewHolder, position: Int, offsetTotal: Int) = Unit

            override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
                if (holder.itemView is Banner) {
                    val banner = holder.itemView
                    banner.layoutParams = VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400)
                    banner.apply {
                        setIndicatorGravity(BannerConfig.CENTER)
                        setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                        setDelayTime(3000)
                        setImages(SCREEN_IMAGE_URLS_DEMO)
                    }
                }
            }
        })

        //Linear
        val layoutHelper1 = LinearLayoutHelper()
        layoutHelper1.bgColor = Color.YELLOW
        layoutHelper1.aspectRatio = 2.0f
        layoutHelper1.setMargin(10, 10, 10, 10)
        layoutHelper1.setPadding(10, 10, 10, 10)
        val layoutHelper2 = LinearLayoutHelper()
        layoutHelper2.aspectRatio = 4.0f
        layoutHelper2.setDividerHeight(10)
        layoutHelper2.setMargin(10, 0, 10, 10)
        layoutHelper2.setPadding(10, 0, 10, 10)
        layoutHelper2.bgColor = 0xFFF5A623.toInt()
//        val mainHandler = Handler(Looper.getMainLooper())
        adapters.add(object : SubAdapter(this, layoutHelper1, 1) {
            override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
//                val subAdapter = this
                //mainHandler.postDelayed(new Runnable() {
                //    @Override
                //    public void run() {
                //        //delegateAdapter.removeAdapter(subAdapter);
                //        //notifyItemRemoved(1);
                //        holder.itemView.setVisibility(View.GONE);
                //        notifyItemChanged(1);
                //        layoutManager.runAdjustLayout();
                //    }
                //}, 2000L);
            }
        })
        adapters.add(object : SubAdapter(this, layoutHelper2, 6) {

            override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
                if (position % 2 == 0) {
                    val layoutParams = VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300)
                    layoutParams.mAspectRatio = 5f
                    holder.itemView.layoutParams = layoutParams
                }
            }
        })

        var layoutHelper = GridLayoutHelper(2).apply {
            setMargin(7, 0, 7, 0)
            setWeights(floatArrayOf(46.665f))
            this.hGap = 3
        }
        adapters.add(SubAdapter(this, layoutHelper, 2))

        layoutHelper = GridLayoutHelper(4)
        with(layoutHelper) {
            setWeights(floatArrayOf(20f, 26.665f))
            setMargin(7, 0, 7, 0)
            this.hGap = 3
        }
        adapters.add(SubAdapter(this, layoutHelper, 8))

        delegateAdapter.setAdapters(adapters)

        val mainHandler = Handler(Looper.getMainLooper())

        trigger = Runnable {
            //recyclerView.scrollToPosition(22);
            //recyclerView.getAdapter().notifyDataSetChanged();
            //mainHandler.postDelayed(trigger, 1000);
            //List<DelegateAdapter.Adapter> newAdapters = new ArrayList<>();
            //newAdapters.add((new SubAdapter(VLayoutActivity.this, new ColumnLayoutHelper(), 3)));
            //newAdapters.add((new SubAdapter(VLayoutActivity.this, new GridLayoutHelper(4), 24)));
            //delegateAdapter.addAdapter(0, new SubAdapter(VLayoutActivity.this, new ColumnLayoutHelper(), 3));
            //delegateAdapter.addAdapter(1, new SubAdapter(VLayoutActivity.this, new GridLayoutHelper(4), 24));
            //delegateAdapter.notifyDataSetChanged();
        }


        mainHandler.postDelayed(trigger, 1000)

        swipe_container.setOnRefreshListener({ mainHandler.postDelayed({ swipe_container.isRefreshing = false }, 2000L) })
        setListenerToRootView()
    }

    fun setListenerToRootView() {
        val activityRootView = window.decorView.findViewById(android.R.id.content)
        activityRootView.viewTreeObserver.addOnGlobalLayoutListener {
            val heightDiff = activityRootView.rootView.height - activityRootView.height
            if (heightDiff > 100) { // 99% of the time the height diff will be due to a keyboard.
                if (!isOpened) {
                    //Do two things, make the view top visible and the editText smaller
                }
                isOpened = true
            } else if (isOpened) {
                isOpened = false
                val recyclerView = rvDemo as RecyclerView
                recyclerView.adapter.notifyDataSetChanged()
            }
        }
    }

}