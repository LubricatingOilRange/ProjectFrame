package com.frame.projectframe.util;

import android.util.Log;

import com.frame.projectframe.BuildConfig;

/**
 * Created by ruibing.han on 2017/12/27.
 */

public class LogUtil {

    public static void i(String msg) {
        if (BuildConfig.DEBUG) {
            Log.i("iii", msg);
        }
    }
}
