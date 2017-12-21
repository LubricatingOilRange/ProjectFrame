package com.frame.projectframe.dagger.module;

import com.frame.projectframe.app.MyApplication;
import com.frame.projectframe.http.helper.HttpHelper;
import com.frame.projectframe.http.helper.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ruibing.han on 2017/12/15.
 */

//封装实现全局单例
@Module
public class AppModule {

    private MyApplication application;

    public AppModule(MyApplication application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public MyApplication provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

}
