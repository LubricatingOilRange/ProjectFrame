package com.frame.projectframe.base.fragment;

/**
 * Created by ruibing.han on 2017/12/12.
 */

interface BaseFragmentImpl {

    /**
     * 获取布局Id
     */
    int getLayoutId();

    /**
     * 添加Receiver,getInternetData，......
     */
    void onViewCreateAndData();
}
