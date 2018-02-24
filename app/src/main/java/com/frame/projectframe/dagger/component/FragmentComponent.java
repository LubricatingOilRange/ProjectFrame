package com.frame.projectframe.dagger.component;

import android.app.Activity;

import com.frame.projectframe.dagger.module.FragmentModule;
import com.frame.projectframe.dagger.scope.FragmentScope;
import com.frame.projectframe.ui.fragment.DemoFragment;

import dagger.Component;

/*
 * Created by 神马都是浮云 on 2017-09-02.
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = {FragmentModule.class})
public interface FragmentComponent {

    Activity getActivity();

    void inject(DemoFragment demoFragment);
}
