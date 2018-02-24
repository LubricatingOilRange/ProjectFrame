package com.frame.projectframe.dagger.component;

import com.frame.projectframe.app.MyApplication;
import com.frame.projectframe.dagger.module.AppModule;
import com.frame.projectframe.dagger.module.HttpModule;
import com.frame.projectframe.http.helper.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/*
 * Created by 神马都是浮云 on 2017-09-02.
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    MyApplication getContext();  // 提供App的Context

    RetrofitHelper retrofitHelper();  //提供http的帮助类
}
