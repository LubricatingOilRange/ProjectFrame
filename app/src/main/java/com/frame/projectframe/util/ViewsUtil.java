package com.frame.projectframe.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

/*
 * Created by ruibing.han on 2017/7/10.
 */

public class ViewsUtil {
    /*
     * 设置View文本
     *
     * @param view
     * @param childViewId
     * @param content
     */
    public static void setText(View view, int childViewId, String content) {
        ((TextView) view.findViewById(childViewId)).setText(content);
    }

    /**
     * 设置控件View可用
     *
     * @param view ()
     */
    public static void setEnable(View view) {
        if (view == null || view.isEnabled()) {
            return;
        }

        view.setEnabled(true);
    }

    /**
     * 设置控件View不可用
     *
     * @param view ()
     */
    public static void setUnEnable(View view) {
        if (view == null || !view.isEnabled()) {
            return;
        }

        view.setEnabled(false);
    }

    /*
     * 设置控件View可见
     *
     * @param view （）
     */
    public static void setVisible(View view) {
        if (view == null) {
            return;
        }
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置控件View不可见
     *
     * @param view （不占空间）
     */
    public static void setGone(View view) {
        if (view == null) {
            return;
        }
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * 设置控件View不可见
     *
     * @param view （占空间）
     */
    public static void setINVisible(View view) {
        if (view == null) {
            return;
        }
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 根据传入的visible 来切换控件View是否可见
     *
     * @param view    （）
     * @param visible ()
     */
    public static void setVisibleOrGone(View view, boolean visible) {
        if (visible) setVisible(view);
        else setGone(view);
    }

    /**
     * 根据传入的visible 来切换控件View是否可见 (当前控件占用控件）
     *
     * @param view    ()
     * @param visible ()
     */
    public static void setVisibleOrINVisible(View view, boolean visible) {
        if (visible) setVisible(view);
        else setINVisible(view);
    }

    /*
     * 获取目标View在屏幕中的位置
     *
     * @param parent （目标View）
     * @return
     */
    public static int[] getViewLocation(int[] location, View parent) {
        parent.getLocationInWindow(location);
        return location;
    }

    /*
     * 获取控件的宽高
     *
     * @param parent
     * @return
     */
    public static int[] getViewWH(View parent) {
        parent.measure(0, 0);
        return new int[]{parent.getMeasuredWidth(), parent.getMeasuredHeight()};
    }

    /*
     * 设置测量模式为UNSPECIFIED可以确保测量不受父View的影响
     *
     * @param view
     */
    public static void measureWidthAndHeight(View view) {

        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        //得到测量宽度
        int mWidth = view.getMeasuredWidth();
        //得到测量高度
        int mHeight = view.getMeasuredHeight();
        Logger.i("mWidth:" + mWidth + "/mHeight:" + mHeight);
    }

    /**
     * 测量view
     */
    public static void measureView(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight, View.MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }
        view.measure(childWidthSpec, childHeightSpec);
    }
}
