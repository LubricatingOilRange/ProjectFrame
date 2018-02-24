package com.frame.projectframe.app;


import android.os.Environment;

import java.io.File;

/*
 * Created by ruibing.han on 2017/12/12.
 */

public class Constant {
    public final static boolean DEBUG = true;

    //-----------------------------response code 请求数据成功后台返回的code-----------------------------//
    /**
     * 由当时情景定义 再次只是列举几个
     */
    public final static String DEFAULT_CODE = "-1";//返回成功数据
    public final static String SUCCESSFUL_CODE = "1";//返回成功数据
    public final static String ERROR_CODE_3000 = "3000";//Token过期
    public final static String ERROR_CODE_6000 = "6006";//系统异常


    //--------------------------------------- file  cache-------------------------------------------------//
    public static final String PATH_CACHE = Environment.getExternalStorageDirectory().getPath();
}
