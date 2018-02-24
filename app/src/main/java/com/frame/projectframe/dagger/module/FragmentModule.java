package com.frame.projectframe.dagger.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.frame.projectframe.dagger.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/*
 * Created by ruibing.han on 2017/12/15.
 */

//只在Fragment可以使用
@Module
public class FragmentModule {

    private Activity activity;

    public FragmentModule(Fragment fragment) {
        this.activity = fragment.getActivity();
    }

    @FragmentScope
    @Provides
    Activity provideActivity() {
        return activity;
    }
}
