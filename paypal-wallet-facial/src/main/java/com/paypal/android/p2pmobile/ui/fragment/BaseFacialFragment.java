package com.paypal.android.p2pmobile.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paypal.android.p2pmobile.ui.activity.FacialActivity;

/*
 * Created by Ruibing.han on 2018/8/7.
 */

public abstract class BaseFacialFragment extends Fragment {

    protected FacialActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (FacialActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;//获取布局Id
        if (inflater != null) {
            view = inflater.inflate(getLayoutId(), null);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewCreatedInit(view);//在onViewCreated方法中可进行布局的初始化操作
    }

    @Override
    public void onStart() {
        super.onStart();
        onStartInit();//在onStart方法中可进行网络请求操作
    }

    protected abstract int getLayoutId();

    protected abstract void onViewCreatedInit(View view);

    protected abstract void onStartInit();

}
