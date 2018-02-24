package com.frame.projectframe.app;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.frame.projectframe.component.service.AppIntentService;
import com.frame.projectframe.dagger.component.AppComponent;
import com.frame.projectframe.dagger.component.DaggerAppComponent;
import com.frame.projectframe.dagger.module.AppModule;
import com.frame.projectframe.dagger.module.HttpModule;
import com.frame.projectframe.ui.view.auto_layout.config.AutoLayoutConfig;
import com.frame.projectframe.util.SPUtil;
import com.frame.projectframe.util.ToastUtil;
import com.orhanobut.logger.Logger;

import java.util.HashSet;
import java.util.Set;

/*
 * Created by ruibing.han on 2017/12/8.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    public static int SCREEN_WIDTH;//屏幕的宽
    public static int SCREEN_HEIGHT;//屏幕的高

    private Set<Activity> allActivities;//保存当前的所有的Activity页面

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);//处理65535问题
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        getScreenSize();//获取屏幕尺寸信息

        initActivityLifecycleCallbacks();//页面所有activity的生命周期，实现是否是前台进程的功能实现

        initComponentCallbacks();//监听内存使用情况，当内存偏低时 进行释放处理

        AppIntentService.initService(instance);//需要通过服务进行初始化的
    }

    public static synchronized MyApplication getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        return DaggerAppComponent.builder()
                .httpModule(new HttpModule())
                .appModule(new AppModule(MyApplication.getInstance()))
                .build();
    }

    /**
     * 初始化屏幕的宽高信息
     */
    private void getScreenSize() {
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(metrics);
        SCREEN_WIDTH = metrics.widthPixels;
        SCREEN_HEIGHT = metrics.heightPixels;
        Logger.i("SCREEN_WIDTH:" + SCREEN_WIDTH + "/SCREEN_HEIGHT:" + SCREEN_HEIGHT);
    }
    /**
     * 前台进程的功能实现
     */
    private int mActivityCount;

    private void initActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                if (allActivities == null) {
                    allActivities = new HashSet<>();
                }
                allActivities.add(activity);//添加activity
            }

            @Override
            public void onActivityStarted(Activity activity) {
                mActivityCount++;
                if (mActivityCount == 1) {//切换到了后台进程
                    Long time = System.currentTimeMillis() - (Long) SPUtil.get(activity, SPUtil.RECORD_TIME, -1L);//获取刚开始切花刀前台进程的时间
                    if (time >= 5 * 60 * 1000) {//5分钟 表示 登陆超时了
                        ToastUtil.getInstance().shortShow("登陆超时了");
                    }
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                mActivityCount--;
                if (mActivityCount == 0) {//切换到了前台进程
                    SPUtil.put(activity, SPUtil.RECORD_TIME, System.currentTimeMillis());//保存切换到后台进程的时间
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (allActivities != null) {
                    allActivities.remove(activity);// 移除activity
                }
            }
        });
    }


    /**
     * 移除所有activity
     */
    public void removeAllActivity() {
        if (allActivities != null && allActivities.size() > 0) {
            synchronized (allActivities.getClass()) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
    }

    /**
     * 移除所有的activity 退出程序
     */
    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities.getClass()) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * 监听内存使用情况
     */
    private void initComponentCallbacks() {
        registerComponentCallbacks(new ComponentCallbacks2() {
            /**
             * Android 4.0后的替代 onLowMemory
             * @param level
             */
            @Override
            public void onTrimMemory(int level) {
                // Android系统会根据当前内存使用的情况，传入对应的级别
                // 下面以清除缓存为例子介绍
                MyApplication.super.onLowMemory();
                /**
                 * 20，60 内存不足的级别 越大越严重
                 * TRIM_MEMORY_RUNNING_MODERATE (5)
                 * TRIM_MEMORY_RUNNING_LOW (10)
                 * TRIM_MEMORY_RUNNING_CRITICAL(15)
                 * TRIM_MEMORY_UI_HIDDEN （20）当应用程序中的所有UI组件全部不可见时
                 * TRIM_MEMORY_BACKGROUND (40)
                 * TRIM_MEMORY_MODERATE(60)
                 * TRIM_MEMORY_COMPLETE (80)
                 */
                if (level >= ComponentCallbacks2.TRIM_MEMORY_MODERATE) {
                    //可在此清除缓存部分缓存文件
                }
            }

            /**
             * 监听 应用程序 配置信息的改变，如屏幕旋转等
             * @param newConfig ()
             */
            @Override
            public void onConfigurationChanged(Configuration newConfig) {

            }

            /**
             * 若想兼容Android 4.0前，请使用OnLowMemory（）
             * Android系统整体内存较低时
             */
            @Override
            public void onLowMemory() {

            }
        });
    }
}
