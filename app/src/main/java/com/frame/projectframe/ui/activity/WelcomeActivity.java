package com.frame.projectframe.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.frame.projectframe.R;
import com.frame.projectframe.base.activity.BaseActivity;
import com.frame.projectframe.http.bean.UserCommand;
import com.frame.projectframe.module.rxjava.flowable.RxFlowCreateUtil;
import com.frame.projectframe.module.rxjava.flowable.RxFlowFilterUtil;
import com.frame.projectframe.module.rxjava.flowable.RxFlowGroupUtil;
import com.frame.projectframe.module.rxjava.flowable.RxFlowTransformUtil;
import com.frame.projectframe.ui.activity.login.LoginActivity;
import com.frame.projectframe.ui.activity.main.MainActivity;
import com.frame.projectframe.util.SPUtil;
import com.frame.projectframe.util.StatusBarUtil;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

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
        Flowable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        UserCommand userCommand = SPUtil.getObject(SPUtil.USER_COMMAND, UserCommand.class);
                        Intent intent;
                        if (userCommand == null) {
                            intent = new Intent(WelcomeActivity.this, LoginActivity.class);//登陆页面
                        } else {
                            intent = new Intent(WelcomeActivity.this, MainActivity.class);
                            intent.putExtra(SPUtil.USER_COMMAND, userCommand);
                        }

                        startActivity(intent);
                    }
                });
    }

    @Override
    public void onEventAndData() {

    }
}
