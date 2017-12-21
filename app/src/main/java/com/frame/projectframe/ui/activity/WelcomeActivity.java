package com.frame.projectframe.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.frame.projectframe.R;
import com.frame.projectframe.base.activity.BaseActivity;
import com.frame.projectframe.module.rxjava.RxBus;
import com.frame.projectframe.util.StatusBarUtil;
import com.frame.projectframe.util.ToastUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

//引导页面
public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.activity_welcome)
    LinearLayout activityWelcome;

    private Disposable mSubscription;

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
                //延迟5秒 发送一个消息
                Flowable.timer(5, TimeUnit.SECONDS)
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                RxBus.getDefault().post("gg");
                            }
                        });

//                Intent intent = new Intent(gg.this, MainActivity.class);
//                startActivity(intent);
//                DialogFragmentHelper.showTestDialog(getSupportFragmentManager());
//                PopupWindowHelper.showNormal( gg.this, activityWelcome,null);

            }
        });
    }

    @Override
    public void onEventAndData() {
        //接收MainActivity回馈的信息
        mSubscription = RxBus.getDefault().toDefaultFlowable(String.class, new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                ToastUtil.shortShow("来自页面：" + s);
            }
        });
        for (int i = 0; i < 10; i++) {
            //BookDaoHelper.getInstance().insertWare(new Book(i,"李"+i,10.0d+i,true));
        }
    }
}
