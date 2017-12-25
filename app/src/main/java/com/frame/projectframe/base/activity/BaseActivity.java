package com.frame.projectframe.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.frame.projectframe.app.MyApplication;
import com.frame.projectframe.ui.view.auto_layout.AutoLayoutActivity;
import com.frame.projectframe.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ruibing.han on 2017/12/12.
 */

public abstract class BaseActivity extends AutoLayoutActivity implements BaseActivityImpl {

    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mBind = ButterKnife.bind(this);
        onViewCreate();//View的初始化操作
        MyApplication.getInstance().addActivity(this);
        onEventAndData();//初始化事件或获取网络数据
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
        MyApplication.getInstance().removeActivity(this);
    }
}
