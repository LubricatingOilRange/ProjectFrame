package com.frame.projectframe.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ruibing.han on 2017/12/12.
 */

public abstract class BaseLazyFragment extends Fragment {

    protected Activity mActivity;
    private Unbinder mBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinder = ButterKnife.bind(this, view);
        onViewCreate();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getUserVisibleHint()) {//是否可见
            getInternetData();//获取网络数据
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    /**
     * 获取布局Id
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化
     */
    protected abstract void onViewCreate();

    /**
     * 当页面属于对用户可见时 获取网络数据实现懒加载
     */
    protected abstract void getInternetData();
}
