package com.frame.projectframe.http.response;

/*
 * Created by codeest on 2016/8/3.
 */
//返回 请求成功结果提示和失败提示
public class ParamTwoResponse<T> {

    private boolean error;
    private T data;

    public boolean isError() {
        return error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
