package com.frame.projectframe.base.activity;

import com.frame.projectframe.app.MyApplication;
import com.frame.projectframe.base.mvp.BasePresenter;
import com.frame.projectframe.base.mvp.BaseView;
import com.frame.projectframe.dagger.component.ActivityComponent;
import com.frame.projectframe.dagger.component.DaggerActivityComponent;
import com.frame.projectframe.dagger.module.ActivityModule;

import javax.inject.Inject;

/*
 * Created by ruibing.han on 2017/9/3.
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    @Inject
    protected T mPresenter;//dagger 直接从activityComponent容器中获取 没有就创建

    //将当前activity 添加到activityComponent容器中
    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(MyApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    public void onViewCreate() {
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    /**
     * 在initViewAndData（）方法前调用
     */


    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
    }

    protected abstract void initInject();//将当前的Activity依赖注入到dagger2中
}
