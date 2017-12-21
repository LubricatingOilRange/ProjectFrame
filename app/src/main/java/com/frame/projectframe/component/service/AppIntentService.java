package com.frame.projectframe.component.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.frame.projectframe.BuildConfig;
import com.frame.projectframe.app.MyApplication;
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
        Logger.i("AppIntentService 已开启", "bbb");

        //初始化数据库
        //GreenDaoManager.getInstance();

        //Logger打印日志的初始化（添加日志附加条件）
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });

        //初始化内存泄漏检测
        LeakCanary.install(MyApplication.getInstance());

        //性能监控组件 找出App界面卡顿元凶（ANR...）
        BlockCanary.install(this, new BlockCanaryContext()).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.i("AppIntentService 已关闭", "bbb");
    }
}
