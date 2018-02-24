package com.frame.projectframe.dagger.module;

import android.app.Activity;


import com.frame.projectframe.dagger.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/*
 * Created by ruibing.han on 2017/12/15.
 */

//只在Activity可以使用
@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return activity;
    }
}
