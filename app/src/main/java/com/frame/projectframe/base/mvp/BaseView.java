package com.frame.projectframe.base.mvp;

import com.frame.projectframe.http.exception.AppException;

/**
 * Created by codeest on 2016/8/2.
 * View基类
 */
public interface BaseView {

    void showErrorMsg(AppException e); //请求失败后返回错误信息
}
