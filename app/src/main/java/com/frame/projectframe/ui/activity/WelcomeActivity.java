package com.frame.projectframe.ui.activity;

import android.view.View;
import android.widget.LinearLayout;

import com.frame.projectframe.R;
import com.frame.projectframe.base.activity.BaseActivity;
import com.frame.projectframe.module.rxjava.flowable.RxFlowCreateUtil;
import com.frame.projectframe.module.rxjava.flowable.RxFlowFilterUtil;
import com.frame.projectframe.module.rxjava.flowable.RxFlowGroupUtil;
import com.frame.projectframe.module.rxjava.flowable.RxFlowTransformUtil;
import com.frame.projectframe.util.StatusBarUtil;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

//引导页面
public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.activity_welcome)
    LinearLayout activityWelcome;

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void onViewCreate() {
        StatusBarUtil.setStatusBarDrawable(WelcomeActivity.this, R.drawable.shape_three);

        findViewById(R.id.iv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxFlowGroupUtil.combineLatest();
            }
        });
    }

    @Override
    public void onEventAndData() {

    }
}
