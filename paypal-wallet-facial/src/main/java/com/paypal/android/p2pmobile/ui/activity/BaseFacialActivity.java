package com.paypal.android.p2pmobile.ui.activity;
/*
 * Created by Ruibing.han on 2018/8/7.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseFacialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        onCreateInit();//在onCreate之后的初始化
    }

    protected abstract int getLayoutId();

    protected abstract void onCreateInit();
}
