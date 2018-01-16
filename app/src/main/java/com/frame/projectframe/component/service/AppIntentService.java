package com.frame.projectframe.component.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.frame.projectframe.BuildConfig;
import com.frame.projectframe.app.MyApplication;
import com.frame.projectframe.ui.view.auto_layout.config.AutoLayoutConfig;
import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;


/**
 * Created by 神马都是浮云 on 2017-09-02.
 */
public class AppIntentService extends IntentService {
    public final static String INTENT_ACTION = "appInitService";

    public AppIntentService() {
        super("appIntentService");
    }

    public static void initService(Context context) {
        Intent intentService = new Intent(context, AppIntentService.class);
        intentService.setAction(INTENT_ACTION);
        context.startService(intentService);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        //65535方法限制处理的初始化
        //MultiDex.install(this);

        //AutoLayout屏幕适配
        AutoLayoutConfig.getInstance().useDeviceSize().init(this);

        //初始化数据库
        //GreenDaoManager.getInstance();

        //Logger打印日志的初始化（添加日志附加条件）
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
