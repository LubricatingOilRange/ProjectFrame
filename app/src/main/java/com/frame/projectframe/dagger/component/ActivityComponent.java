package com.frame.projectframe.dagger.component;

import android.app.Activity;

import com.frame.projectframe.dagger.module.ActivityModule;
import com.frame.projectframe.dagger.scope.ActivityScope;
import com.frame.projectframe.ui.activity.login.LoginActivity;
import com.frame.projectframe.ui.activity.main.MainActivity;

import dagger.Component;

/**
 * Created by 神马都是浮云 on 2017-09-02.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity getActivity();

    void inject(LoginActivity loginActivity);//登录页面

    void inject(MainActivity mainActivity);//主页面

}
