package com.frame.projectframe.util;

import com.frame.projectframe.http.encrypt.DecodeConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitUtil {

    /*
     * 获取OkHttpClient.Builder
     *
     * @return
     */
    public static Retrofit.Builder getRetrofit_Builder() {
        return new Retrofit.Builder();
    }

    /*
     * @param builder
     * @param client
     * @param url
     */
    public static Retrofit getRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(DecodeConverterFactory.create(GsonUtil.defaultGson()))
                .build();
    }
}
