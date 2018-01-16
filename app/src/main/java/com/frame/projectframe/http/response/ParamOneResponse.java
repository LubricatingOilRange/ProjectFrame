package com.frame.projectframe.http.response;

/**
 * Created by codeest on 16/11/27.
 */

//只返回结果
public class ParamOneResponse<T> {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
