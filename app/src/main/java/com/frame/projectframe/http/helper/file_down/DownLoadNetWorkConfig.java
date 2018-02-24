package com.frame.projectframe.http.helper.file_down;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


class DownLoadNetWorkConfig {
    //  BASE_URL,因为测试用的自己服务器，为了缓解自己服务器压力，所以请各位自己填写url
    private static final String BASE_URL = "http://180.168.134.212:18601/";
    private static final int DEFAULT_TIMEOUT = 20;

    static Retrofit getRetrofit(DownLoadListener listener) {
        DownLoadProgressInterceptor interceptor = new DownLoadProgressInterceptor(listener);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)//设置失败后重新请求一次
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
