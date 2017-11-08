package com.ysdemo.mykotlin.ui.vlayout;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Song on 2017/9/21.
 */
public class DemoViewHolder extends RecyclerView.ViewHolder {

    public static volatile int existing = 0;
    public static int createdTimes = 0;

    public DemoViewHolder(View itemView) {
        super(itemView);
        createdTimes++;
        existing++;
    }

    @Override
    protected void finalize() throws Throwable {
        existing--;
        super.finalize();
    }
}
