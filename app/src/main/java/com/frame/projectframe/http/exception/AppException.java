package com.frame.projectframe.http.exception;

/**
 * Created by ruibing.han on 2017/12/15.
 */

//自定义一个异常类
public class AppException extends Exception {
    private String code;

    public AppException(String msg) {
        super(msg);
    }

    public AppException(String msg, String code) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
