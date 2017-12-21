package com.frame.projectframe.ui.view.recycler_view;

import android.view.View;

/**
 * Created by ruibing.han on 2017/11/23.
 */

public interface OnRVItemClickListener {
    /**
     * RecyclerView的子条目的点击事件
     *
     * @param view
     * @param position
     */
    void onItemClick(View view, int position);
}
