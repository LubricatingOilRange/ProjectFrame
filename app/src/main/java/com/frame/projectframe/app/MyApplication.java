package com.frame.projectframe.app;

import android.app.Activity;
import android.app.Application;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.frame.projectframe.component.service.AppIntentService;
import com.frame.projectframe.dagger.component.AppComponent;
import com.frame.projectframe.dagger.component.DaggerAppComponent;
import com.frame.projectframe.dagger.module.AppModule;
import com.frame.projectframe.dagger.module.HttpModule;
import com.frame.projectframe.ui.view.auto_layout.config.AutoLayoutConfig;
import com.orhanobut.logger.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ruibing.han on 2017/12/8.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    public static int SCREEN_WIDTH;//屏幕的宽
    public static int SCREEN_HEIGHT;//屏幕的高

    private Set<Activity> allActivities;//保存当前的所有的Activity页面

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        getScreenSize();//获取屏幕尺寸信息

        AutoLayoutConfig.getInstance().useDeviceSize().init(this);//AutoLayout屏幕适配

        //MultiDex.install(this);//65535方法限制处理的初始化

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
     * 添加activity
     *
     * @param act
     */
    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    /**
     * 移除activity
     *
     * @param act
     */
    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
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
}
