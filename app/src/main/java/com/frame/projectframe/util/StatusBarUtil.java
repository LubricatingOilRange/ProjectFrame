package com.frame.projectframe.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by ruibing.han on 2017/10/9.
 */

public class StatusBarUtil {

    /**
     * 获取顶部状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        try {
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取底部导航栏高度
     */
    public static int getNavigationBarHeight(Activity activity) {
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    /**
     * 设置页面最外层布局 FitsSystemWindows 属性
     *
     * @param activity
     */
    public static void fitsSystemWindows(Activity activity) {
        ViewGroup contentFrameLayout = (ViewGroup) activity.findViewById(android.R.id.content);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            //布局预留状态栏高度的 padding
            parentView.setFitsSystemWindows(true);
            if (parentView instanceof DrawerLayout) {
                DrawerLayout drawer = (DrawerLayout) parentView;
                //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
                drawer.setClipToPadding(false);
            }
        }
    }

    /**
     * 根据系统API 来更改状态栏的颜色
     *
     * @param activity
     */
    public static void setStatusBar(Activity activity, @ColorInt int color) {
        Window window = activity.getWindow();
        setStatusBar(window, color, Color.TRANSPARENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//    >=19
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {//   19 - 21
                addColorView(window, color);
            }
        }
        fitsSystemWindows(activity);
    }

    /**
     * 更改占位空间的背景颜色来实现状态栏的颜色
     *
     * @param activity
     * @param color
     */
    public static void setStatusBarColor(Activity activity, @ColorInt int color) {
        Window window = activity.getWindow();
        setStatusBar(window, Color.TRANSPARENT, Color.TRANSPARENT);//设置全屏
        addColorView(window, color);
        fitsSystemWindows(activity);
    }

    /**
     * 更改占位空间的背景Drawable来实现状态栏的渐变颜色
     *
     * @param activity
     * @param drawableRes
     */
    public static void setStatusBarDrawable(Activity activity, @DrawableRes int drawableRes) {
        Window window = activity.getWindow();
        setStatusBar(window, Color.TRANSPARENT, Color.TRANSPARENT);//设置全屏
        addDrawableView(window, drawableRes);
        fitsSystemWindows(activity);
    }

    /**
     * 通过设置全屏，设置状态栏透明
     *
     * @param window
     */
    private static void setStatusBar(Window window, @ColorInt int statusBarColor, @ColorInt int navigationBarColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//    >=19
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//   >=21
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(statusBarColor);
                //导航栏颜色也可以正常设置
                window.setNavigationBarColor(navigationBarColor);
            } else {//19 - 21
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }

    /**
     * 添加一个Color背景占位
     *
     * @param window
     * @param color  状态栏的颜色
     */
    public static void addColorView(Window window, @ColorInt int color) {
        ViewGroup decorView = (ViewGroup) window.getDecorView();
        View statusBarView = new View(window.getContext());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(window.getContext()));
        statusBarView.setBackgroundColor(color);
        decorView.addView(statusBarView, lp);
    }

    /**
     * 添加一个Drawable占位
     *
     * @param window
     * @param drawableRes 状态栏的背景Drawable
     */
    public static void addDrawableView(Window window, @DrawableRes int drawableRes) {
        ViewGroup decorView = (ViewGroup) window.getDecorView();
        View statusBarView = new View(window.getContext());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(window.getContext()));
        statusBarView.setBackgroundResource(drawableRes);
        decorView.addView(statusBarView, lp);
    }
}

