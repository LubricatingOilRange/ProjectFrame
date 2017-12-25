package com.frame.projectframe.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.frame.projectframe.R;
import com.frame.projectframe.app.MyApplication;

/*
 * Created by codeest on 2016/8/4.
 */
public class ToastUtil {
    @SuppressLint("StaticFieldLeak")
    private static ToastUtil sInstance;

    private Context mContext;

    public ToastUtil(Context context) {
        this.mContext = context;
    }

    public static ToastUtil getInstance() {
        if (sInstance == null) {
            synchronized (ToastUtil.class) {
                if (sInstance == null) {
                    sInstance = new ToastUtil(MyApplication.getInstance());
                }
            }
        }

        return sInstance;
    }

    /*
     * 展示短 Toast
     *
     * @param msg
     */
    public void shortShow(String msg) {
        createShortToast(msg, Toast.LENGTH_SHORT).show();
    }

    /*
     * 展示长 Toast
     *
     * @param msg
     */
    public void longToast(String msg) {
        createShortToast(msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 取消 Toast
     */
    public void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

    /**
     * 将Toast 置空
     */
    public void clearToast() {
        if (toast != null) {
            toast.cancel();
            toast = null;
            tv_toast_msg = null;
        }
    }


    /**
     * Toast自定义的创建
     */
    private TextView tv_toast_msg;
    private Toast toast;

    private Toast createShortToast(String msg, int type) {
        if (toast == null) {
            View contentView = View.inflate(mContext, R.layout.layout_toast, null);
            tv_toast_msg = (TextView) contentView.findViewById(R.id.tv_toast_msg);
            toast = new Toast(mContext);
            toast.setView(contentView);
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        toast.setDuration(type);
        tv_toast_msg.setText(msg);
        return toast;
    }
}
