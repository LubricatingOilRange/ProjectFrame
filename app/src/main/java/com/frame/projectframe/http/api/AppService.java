package com.frame.projectframe.http.api;

import com.frame.projectframe.http.bean.UserCommand;
import com.frame.projectframe.http.response.BaseResponse;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by ruibing.han on 2017/12/15.
 */

public interface AppService {

    String HOST = "http://180.168.134.212:18601/";

    //登陆页面
    @POST("shopdog/auth/login")
    Flowable<BaseResponse<UserCommand>> login(@QueryMap Map<String, String> map);
}
