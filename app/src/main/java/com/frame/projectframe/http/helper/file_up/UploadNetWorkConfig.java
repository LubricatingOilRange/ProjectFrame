package com.frame.projectframe.http.helper.file_up;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JokAr on 2017/3/6.
 */

public class UploadNetWorkConfig {
    //  BASE_URL,因为测试用的自己服务器，为了缓解自己服务器压力，所以请各位自己填写url
    private static final String BASE_URL = "http://180.168.134.212:18601/";
    private static final int DEFAULT_TIMEOUT = 20;

    public static Retrofit getRetrofit(UploadListener listener) {
        UpLoadProgressInterceptor interceptor = new UpLoadProgressInterceptor(listener);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL + "/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
