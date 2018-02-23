package com.frame.projectframe.http.api;

import com.frame.projectframe.http.bean.UserCommand;
import com.frame.projectframe.http.response.ParamThreeResponse;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

/**
 * Created by ruibing.han on 2017/12/15.
 */

public interface AppService {

    String HOST = "http://180.168.134.212:18601/";

    //登陆页面
    @POST("shopdog/auth/login")
    Flowable<ParamThreeResponse<UserCommand>> login(@QueryMap Map<String, String> map);

    //----------------------------------------- 图片（文件）上传-----------------------------
    @Multipart
    @POST("your address")
    Flowable<String> upLoad(@PartMap Map<String, RequestBody> parameterMap, @Part("imgs") MultipartBody body);
}
