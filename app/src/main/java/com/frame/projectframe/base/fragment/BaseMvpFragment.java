package com.frame.projectframe.base.fragment;

import com.frame.projectframe.app.MyApplication;
import com.frame.projectframe.base.mvp.BasePresenter;
import com.frame.projectframe.base.mvp.BaseView;
import com.frame.projectframe.dagger.component.ActivityComponent;
import com.frame.projectframe.dagger.component.DaggerActivityComponent;
import com.frame.projectframe.dagger.component.DaggerFragmentComponent;
import com.frame.projectframe.dagger.component.FragmentComponent;
import com.frame.projectframe.dagger.module.ActivityModule;
import com.frame.projectframe.dagger.module.FragmentModule;

import javax.inject.Inject;

/*
 * Created by ruibing.han on 2017/12/25.
 */

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {
    @Inject
    protected T mPresenter;//dagger 直接从activityComponent容器中获取 没有就创建

    //将当前activity 添加到activityComponent容器中
    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(MyApplication.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreateAndData() {
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {

        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroyView();
    }

    protected abstract void initInject();//将当前的Activity依赖注入到dagger2中
}
