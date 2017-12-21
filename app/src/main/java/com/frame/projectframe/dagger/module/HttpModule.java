package com.frame.projectframe.dagger.module;

import com.frame.projectframe.dagger.qualiter.AppUrl;
import com.frame.projectframe.dagger.qualiter.OtherUrl;
import com.frame.projectframe.http.api.AppService;
import com.frame.projectframe.http.api.OtherService;
import com.frame.projectframe.util.OkHttpUtil;
import com.frame.projectframe.util.RetrofitUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by 神马都是浮云 on 2017-09-02.
 */
@Module
public class HttpModule {

    //将OkHttpClient.Builder添加到AppComponent容器中
    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return OkHttpUtil.getOkHttpClient_Builder();
    }

    /**
     * OkHttpClient 的创建
     *
     * @param builder
     * @return
     */
    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        return OkHttpUtil.getOkHttpClient(builder);
    }

    //Retrofit.Builder
    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return RetrofitUtil.getRetrofit_Builder();
    }

    //--------------------------appUrl--------------------------------------------//
    //appUrl的Retrofit
    @Singleton
    @AppUrl
    @Provides
    Retrofit provideAppRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return RetrofitUtil.getRetrofit(builder, client, AppService.HOST);
    }

    @Singleton
    @Provides
    AppService provideAppService(@AppUrl Retrofit retrofit) {
        return retrofit.create(AppService.class);
    }
    //--------------------------otherUrl--------------------------------------------//

    //otherUrl的Retrofit
    @Singleton
    @OtherUrl
    @Provides
    Retrofit provideOtherRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return RetrofitUtil.getRetrofit(builder, client, OtherService.HOST);
    }

    //otherUrl的Service
    @Singleton
    @Provides
    OtherService provideOtherService(@OtherUrl Retrofit retrofit) {
        return retrofit.create(OtherService.class);
    }
}
