package com.frame.projectframe.ui.view.recycler_view;

import android.view.View;

/**
 * Created by ruibing.han on 2017/11/23.
 */

public interface OnRVItemChildClickListener {

    /**
     * RecyclerView中子条目的点击事件和该子条目的索引
     * 例子：子条目中有个Button按钮 当前点击该按钮的时候 返回该按钮所在条目的索引
     *
     * @param view
     * @param position
     */
    void onItemChildClick(View view, int position);
}
