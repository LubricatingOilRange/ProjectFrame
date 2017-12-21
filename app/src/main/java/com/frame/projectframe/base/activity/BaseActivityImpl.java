package com.frame.projectframe.base.activity;

/**
 * Created by ruibing.han on 2017/12/12.
 */

interface BaseActivityImpl {
    /**
     * 获取布局Id
     */
    int getLayoutId();

    /**
     * View的初始化，setText，OnClickListener，......
     */
    void onViewCreate();

    /**
     * 添加Receiver,getInternetData，......
     */
    void onEventAndData();
}
