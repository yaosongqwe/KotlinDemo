package com.ysdemo.mykotlin.ui.gank

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ysdemo.mykotlin.R
import com.ysdemo.mykotlin.ui.gank.model.GankNormalItem
import kotlinx.android.synthetic.main.recycler_item_welfare.view.*

class GirlsAdapter(private val fragment: Fragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<GankNormalItem>()

    var curPage = 0
        private set

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClickItem(view: View, item: GankNormalItem)
    }

    fun updateData(page: Int, list: List<GankNormalItem>?) {
        if (null == list || list.isEmpty()) return
        if (page - curPage > 1) return
        if (page <= 1 && !items.containsAll(list)) {
            items.clear()
            items.addAll(list)
            notifyDataSetChanged()

        } else if (1 == page - curPage) {
            val oldSize = items.size
            items.addAll(oldSize, list)
            notifyItemRangeInserted(oldSize, list.size)
        }
        curPage = page
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_welfare, parent, false)
        itemView.setOnClickListener { view ->
            val tag = view.tag
            if (null != tag && tag is Int) {
                onItemClickListener?.onClickItem(view, items[tag])
            }
        }
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.itemView.tag = position
            val item = items[position]
            Glide.with(fragment)
                    .load(item.gank.url)
                    .centerCrop()
                    .placeholder(R.color.imageColorPlaceholder)
                    .into(holder.itemView.girl_image)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.girl_image.ratio = 0.618f
        }
    }
}
